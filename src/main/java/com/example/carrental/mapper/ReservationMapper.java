package com.example.carrental.mapper;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.dto.ReservationDto;
import com.example.carrental.entity.Reservation;
import com.example.carrental.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationMapper {

    private CarMapper carMapper;
    private CostumerMapper costumerMapper;


    public Reservation mapToEntity(ReservationDto reservationDto){
        Reservation reservation  =new Reservation();
        reservation.setReservation_id(reservationDto.getReservation_id());
        reservation.setDateOfBooking(reservationDto.getDateOfBooking());
        reservation.setDepartureDate(reservationDto.getDepartureDate());
        reservation.setReturnDate(reservationDto.getReturnDate());
        reservation.setPickUpDepartment(reservationDto.getPickUpDepartment());
        reservation.setReturnDepartment(reservationDto.getReturnDepartment());

        return reservation;

    }

    public ReservationDto mapToDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservation_id(reservation.getReservation_id());
        reservationDto.setDateOfBooking(reservation.getDateOfBooking());
        reservationDto.setDepartureDate(reservation.getDepartureDate());
        reservationDto.setReturnDate(reservation.getReturnDate());
        reservationDto.setCostumerId(reservation.getCostumer().getId());
        reservationDto.setPickUpDepartment(reservation.getPickUpDepartment());
        reservationDto.setReturnDepartment(reservation.getReturnDepartment());
        reservationDto.setCostumerDto(costumerMapper.mapToDto(reservation.getCostumer()));
        reservationDto.setAmount(reservation.getAmount());
        reservationDto.setCarId(reservation.getCar().getId());
        reservationDto.setCarDto(carMapper.mapToDto(reservation.getCar()));

        return reservationDto;
    }

}