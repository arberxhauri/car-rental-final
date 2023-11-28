package com.example.carrental.repository;

import com.example.carrental.entity.Bill;
import com.example.carrental.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findBillByReservation(Reservation reservation);
}
