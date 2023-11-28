package com.example.carrental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "REFUND")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    private LocalDate returnDate;

    @OneToOne
    @JoinColumn(name = "reservationId", nullable = false)
    private Reservation reservation;

    private double surcharge;

    private String comments;


}
