package com.example.carrental.controller;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.entity.Refund;
import com.example.carrental.entity.Reservation;
import com.example.carrental.service.RefundService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    private RefundService refundService;

    @PostMapping("/cancelRes")
    public RefundDto cancelReservation(@RequestBody RefundDto refundDto){
        return  refundService.cancelReservation(refundDto);
    }

    @PostMapping("/notAvailable")
    public RefundDto carNotAvailable(@RequestBody RefundDto refundDto){
        return  refundService.carNotAvailable(refundDto);
    }
}
