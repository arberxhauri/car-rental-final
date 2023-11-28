package com.example.carrental.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {

    private long reservation_id;


    private LocalDate dateOfBooking;

    private long costumerId;

    private CostumerDto costumerDto;

    private long carId;

    private CarDto carDto;

    private LocalDate departureDate;

    private LocalDate returnDate;

    private String pickUpDepartment;

    private String returnDepartment;

    private double amount;

}