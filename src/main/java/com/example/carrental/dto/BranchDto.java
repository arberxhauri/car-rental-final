package com.example.carrental.dto;

import com.example.carrental.entity.Car;
import com.example.carrental.entity.Employee;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BranchDto {

    private long id;

    private String city;

    private long rentalId;

    private RentalDto rentalDto;
}
