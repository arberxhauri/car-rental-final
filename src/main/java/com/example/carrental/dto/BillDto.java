package com.example.carrental.dto;

import com.example.carrental.entity.Refund;
import com.example.carrental.entity.Reservation;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDto {

    private long id;

    private long reservationId;

    private ReservationDto reservationDto;

    private double amount;
}
