package com.example.carrental.service;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.entity.Employee;
import com.example.carrental.entity.Refund;
import com.example.carrental.entity.Reservation;
import com.example.carrental.mapper.RefundMapper;
import com.example.carrental.mapper.ReservationMapper;
import com.example.carrental.repository.EmployeeRepository;
import com.example.carrental.repository.RefundRepository;
import com.example.carrental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RefundService {

    private RefundRepository refundRepository;
    private RefundMapper refundMapper;
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;
    private ReservationMapper reservationMapper;
    private EmployeeRepository employeeRepository;

    public RefundDto cancelReservation(RefundDto refundDto){
        Reservation existingReservation = reservationRepository.findById(refundDto.getReservationId()).orElseThrow(() ->
                new RuntimeException("Reservation with id " + refundDto.getReservationId() + " was not found!"));
        Employee existingEmployee = employeeRepository.findById(refundDto.getEmployeeId()).orElseThrow(() ->
                new RuntimeException("Employee with id " + refundDto.getEmployeeId() + " was not found!"));

        Refund refund = refundMapper.mapToEntity(refundDto);
        refund.setReservation(existingReservation);
        refund.setEmployee(existingEmployee);
        cancelReservationRefund(existingReservation, refund);

        Refund savedRefund = refundRepository.save(refund);

        return refundMapper.mapToDto(savedRefund);
    }

    public RefundDto carNotAvailable(RefundDto refundDto){
        Reservation existingReservation = reservationRepository.findById(refundDto.getReservationId()).orElseThrow(() ->
                new RuntimeException("Reservation with id " + refundDto.getReservationId() + " was not found!"));
        Employee existingEmployee = employeeRepository.findById(refundDto.getEmployeeId()).orElseThrow(() ->
                new RuntimeException("Employee with id " + refundDto.getEmployeeId() + " was not found!"));

        Refund refund = refundMapper.mapToEntity(refundDto);
        refund.setReservation(existingReservation);
        refund.setEmployee(existingEmployee);
        refund.setSurcharge(existingReservation.getAmount());

        Refund savedRefund = refundRepository.save(refund);
        existingReservation.setAmount(existingReservation.getAmount() - refund.getSurcharge());
        reservationService.updateReservation(existingReservation.getReservation_id(), reservationMapper.mapToDto(existingReservation));
        return refundMapper.mapToDto(savedRefund);
    }

    public double cancelReservationRefund(Reservation reservation, Refund refund) {
        if (ChronoUnit.DAYS.between(refund.getReturnDate(), reservation.getDepartureDate()) >= 2) {
            refund.setSurcharge(reservation.getAmount() - (reservation.getAmount() * 0.2));
            reservation.setAmount(reservation.getAmount() - refund.getSurcharge());
            reservationService.updateReservation(reservation.getReservation_id(), reservationMapper.mapToDto(reservation));
        }else {
            refund.setSurcharge(0);
        }
        return 0;
    }
}
