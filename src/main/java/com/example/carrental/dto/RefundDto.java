package com.example.carrental.dto;

import com.example.carrental.entity.Employee;
import com.example.carrental.entity.Reservation;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RefundDto {
    private long id;

    private long employeeId;

    private EmployeeDto employeeDto;

    private LocalDate returnDate;

    private long reservationId;

    private ReservationDto reservationDto;

    private double surcharge;

    private String comments;
}
