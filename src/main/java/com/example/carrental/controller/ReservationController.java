package com.example.carrental.controller;

import com.example.carrental.dto.ReservationDto;
import com.example.carrental.service.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save")
    public ReservationDto saveReservation(@Valid @RequestBody ReservationDto reservationDto) {
        return reservationService.saveReservation(reservationDto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{reservation_id}")
    public ReservationDto getReservationById(@Valid @RequestBody @PathVariable(name = "reservation_id") Long reservation_id) {
        return reservationService.getReservationById(reservation_id);
    }

    @PutMapping("/update/{reservation_id}")
    public ReservationDto updateReservation(@PathVariable(name = "reservation_id") Long reservation_id, @RequestBody ReservationDto updatedReservation) {
        return reservationService.updateReservation(reservation_id, updatedReservation);
    }

    @DeleteMapping("/delete/{reservation_id}")
    public String deleteReservation(@PathVariable(name = "reservation_id") Long reservation_id) {
        reservationService.deleteReservation(reservation_id);

        return "Reservation Successfully deleted!";
    }


}
