package com.example.carrental.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalDto {
    private long id;

    private String name;

    private String contactAddress;

    private String owner;
}
