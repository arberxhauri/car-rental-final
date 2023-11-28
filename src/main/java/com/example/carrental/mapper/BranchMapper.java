package com.example.carrental.mapper;

import com.example.carrental.dto.BranchDto;
import com.example.carrental.dto.RentalDto;
import com.example.carrental.entity.Branch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BranchMapper {

    private RentalMapper rentalMapper;

    public Branch mapToEntity(BranchDto branchDto){
        Branch branch = new Branch();
        branch.setId(branchDto.getId());
        branch.setCity(branchDto.getCity());

        return branch;
    }

    public BranchDto mapToDto(Branch branch){
        BranchDto branchDto = new BranchDto();
        branchDto.setId(branch.getId());
        branchDto.setCity(branch.getCity());
        branchDto.setRentalId(branch.getRental().getId());
        branchDto.setRentalDto(rentalMapper.mapToDto(branch.getRental()));

        return branchDto;
    }
}
