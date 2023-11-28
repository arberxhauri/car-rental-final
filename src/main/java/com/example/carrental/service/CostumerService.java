package com.example.carrental.service;

import com.example.carrental.dto.CostumerDto;
import com.example.carrental.entity.Costumer;
import com.example.carrental.entity.Rental;
import com.example.carrental.mapper.CostumerMapper;
import com.example.carrental.repository.CostumerRepository;
import com.example.carrental.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CostumerService {

    private CostumerRepository costumerRepository;
    private CostumerMapper costumerMapper;
    private RentalRepository rentalRepository;

    public CostumerDto save(CostumerDto costumerDto) {
        Rental existingRental = rentalRepository.findById(costumerDto.getRentalId()).orElseThrow(() ->
                new RuntimeException("Rental with id " + costumerDto.getRentalId() + " was not found!"));

        Costumer costumer = costumerMapper.mapToEntity(costumerDto);
        costumer.setRental(existingRental);

        Costumer savedCostumer = costumerRepository.save(costumer);

        return costumerMapper.mapToDto(savedCostumer);
    }

    public List<CostumerDto> findAll() {
        List<Costumer> costumers = costumerRepository.findAll();

        return costumers.stream().map(costumer -> costumerMapper.mapToDto(costumer)).collect(Collectors.toList());
    }

    public CostumerDto findById(long costumerId) {
        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));

        return costumerMapper.mapToDto(existingCostumer);
    }

    public CostumerDto update(CostumerDto costumerDto, long costumerId) {
        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));

        Rental existingRental = rentalRepository.findById(costumerDto.getRentalId()).orElseThrow(() ->
                new RuntimeException("Rental with id " + costumerDto.getRentalId() + " was not found!"));

        Costumer costumer = costumerMapper.mapToEntity(costumerDto);
        costumer.setId(costumerId);
        costumer.setRental(existingRental);

        Costumer savedCostumer = costumerRepository.save(costumer);

        return costumerMapper.mapToDto(savedCostumer);
    }

    public String delete(long costumerId) {
        Costumer existingCostumer = costumerRepository.findById(costumerId).orElseThrow(() ->
                new RuntimeException("Costumer with id " + costumerId + " was not found!"));
        costumerRepository.delete(existingCostumer);

        return "Costumer with id: " + costumerId + " was deleted successfully";
    }
}