package com.example.carrental.service;

import com.example.carrental.dto.ReservationDto;
import com.example.carrental.entity.Car;
import com.example.carrental.entity.Costumer;
import com.example.carrental.entity.Reservation;
import com.example.carrental.mapper.ReservationMapper;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CostumerRepository;
import com.example.carrental.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private CarRepository carRepository;
    private CostumerRepository costumerRepository;
    private BillService billService;

    public ReservationDto saveReservation(ReservationDto reservationDto) {
        Car existingCar = carRepository.findById(reservationDto.getCarId()).orElseThrow(() ->
                new RuntimeException("Car not found!"));
        Costumer existingCostumer = costumerRepository.findById(reservationDto.getCostumerId()).orElseThrow(() ->
                new RuntimeException("Costumer not found!"));
        Reservation reservation = reservationMapper.mapToEntity(reservationDto);
        reservation.setCar(existingCar);
        reservation.setCostumer(existingCostumer);
        reservation.setAmount(reservation.calcAmount(existingCar.getAmount()));
        reservation.validate();
        Reservation savedReservation = reservationRepository.save(reservation);
        billService.resTransaction(reservation.getReservation_id());
        return reservationMapper.mapToDto(savedReservation);
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();

        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservationDtoList.add(reservationMapper.mapToDto(reservation));
        }
        return reservationDtoList;
    }

    public ReservationDto getReservationById(Long id) {

        Reservation existingReservation = reservationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Reservation with id:" + id + " not found"));

        return reservationMapper.mapToDto(existingReservation);


    }

    public ReservationDto updateReservation(Long id, ReservationDto updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Reservation with id: " + id + " not found!"));
        Car existingCar = carRepository.findById(updatedReservation.getCarId()).orElseThrow(() ->
                new RuntimeException("Car not found!"));
        Costumer existingCostumer = costumerRepository.findById(updatedReservation.getCostumerId()).orElseThrow(() ->
                new RuntimeException("Costumer not found!"));

        Reservation reservation = reservationMapper.mapToEntity(updatedReservation);
        reservation.setReservation_id(id);
        reservation.setCar(existingCar);
        reservation.setCostumer(existingCostumer);
        reservation.setAmount(reservation.calcAmount(existingCar.getAmount()));
        reservation.validate();

        Reservation savedReservation = reservationRepository.save(existingReservation);
        billService.updateResTransaction(existingReservation.getReservation_id());
        return reservationMapper.mapToDto(savedReservation);


    }

    public void deleteReservation(Long id) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);

        if (existingReservation.isPresent()) {
            reservationRepository.delete(existingReservation.get());
        } else {
            throw new RuntimeException("Reservation not found with ID: " + id);
        }
    }


}
