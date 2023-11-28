package com.example.carrental.service;

import com.example.carrental.dto.BranchDto;
import com.example.carrental.entity.Branch;
import com.example.carrental.entity.Rental;
import com.example.carrental.mapper.BranchMapper;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BranchService {

    private BranchRepository branchRepository;
    private BranchMapper branchMapper;
    private RentalRepository rentalRepository;

    public BranchDto save(BranchDto branchDto){
        Rental existingRental = rentalRepository.findById(branchDto.getRentalId()).orElseThrow(() ->
                new RuntimeException("Rental with id " + branchDto.getRentalId() + " was not found!"));

        Branch branch = branchMapper.mapToEntity(branchDto);
        branch.setRental(existingRental);

        Branch savedRental = branchRepository.save(branch);

        return branchMapper.mapToDto(savedRental);
    }

    public List<BranchDto> findAll(){
        List<Branch> branches = branchRepository.findAll();

        return branches.stream().map(branch -> branchMapper.mapToDto(branch)).collect(Collectors.toList());
    }

    public BranchDto findById(long branchId){
        Branch branch = branchRepository.findById(branchId).orElseThrow(() ->
                new RuntimeException("Branch with id " + branchId + " was not found!"));

        return branchMapper.mapToDto(branch);
    }

    public BranchDto update(BranchDto branchDto, long branchId){
        Branch branch = branchRepository.findById(branchId).orElseThrow(() ->
                new RuntimeException("Branch with id " + branchId + " was not found!"));
        Rental existingRental = rentalRepository.findById(branchDto.getRentalId()).orElseThrow(() ->
                new RuntimeException("Rental with id " + branchDto.getRentalId() + " was not found!"));

        branch.setCity(branchDto.getCity());
        branch.setRental(existingRental);

        Branch savedBranch = branchRepository.save(branch);

        return branchMapper.mapToDto(savedBranch);
    }
}