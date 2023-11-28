package com.example.carrental.dto;

import com.example.carrental.entity.Branch;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private long id;

    private  String firstName;

    private String name;

    private String position;

    private long branchId;

    private BranchDto branchDto;
}
