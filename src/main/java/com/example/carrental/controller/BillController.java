package com.example.carrental.controller;

import com.example.carrental.dto.BillDto;
import com.example.carrental.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/bills")
public class BillController {

    private BillService billService;

    @GetMapping("/list")
    public List<BillDto> findAll() {
        return billService.findAll();
    }

    @GetMapping("/total")
    public double total() {
        return billService.totalAmount();
    }
}
