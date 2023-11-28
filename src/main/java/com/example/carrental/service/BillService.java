package com.example.carrental.service;

import com.example.carrental.dto.BillDto;
import com.example.carrental.entity.Bill;
import com.example.carrental.entity.Refund;
import com.example.carrental.entity.Reservation;
import com.example.carrental.mapper.BillMapper;
import com.example.carrental.repository.BillRepository;
import com.example.carrental.repository.RefundRepository;
import com.example.carrental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillService {

    private BillRepository billRepository;
    private BillMapper billMapper;
    private ReservationRepository reservationRepository;
    private RefundRepository refundRepository;


    public List<BillDto> findAll() {
        List<Bill> bills = billRepository.findAll();

        return bills.stream().map(bill -> billMapper.mapToDto(bill)).collect(Collectors.toList());
    }

    public double totalAmount() {
        double sum = 0;
        List<Bill> bills = billRepository.findAll();
        for (Bill bill : bills) {
            sum = sum + bill.getAmount();
        }
        return sum;
    }

    public Bill resTransaction(long reservationId) {
        Reservation existingReservation = reservationRepository.findById(reservationId).orElseThrow(() ->
                new RuntimeException("Reservation with id " + reservationId + " was not found!"));
        Bill bill = new Bill();
        bill.setReservation(existingReservation);
        bill.setAmount(existingReservation.getAmount());

        return billRepository.save(bill);
    }

    public Bill updateResTransaction(long reservationId) {
        Reservation existingReservation = reservationRepository.findById(reservationId).orElseThrow(() ->
                new RuntimeException("Reservation with id " + reservationId + " was not found!"));
        Bill existingBill = billRepository.findBillByReservation(existingReservation).orElseThrow(() ->
                new RuntimeException("Bill with reservation id " + reservationId + " was not found!"));

        existingBill.setAmount(existingReservation.getAmount());

        return existingBill;
    }
}
