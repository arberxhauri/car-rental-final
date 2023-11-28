package com.example.carrental.controller;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private RentalService rentalService;

    @PostMapping("save")
    public RentalDto save(@RequestBody RentalDto rentalDto) {
        return rentalService.save(rentalDto);
    }

    @GetMapping("list")
    public List<RentalDto> findAll() {
        return rentalService.findAll();
    }

    @GetMapping("{rentalId}")
    public RentalDto findById(@PathVariable(name = "rentalId") long rentalId) {
        return rentalService.findById(rentalId);
    }

    @PutMapping("{rentalId}")
    public RentalDto update(@RequestBody RentalDto rentalDto, @PathVariable(name = "rentalId") long rentalId) {
        return rentalService.update(rentalDto, rentalId);
    }

    @DeleteMapping("{rentalId}")
    public String delete(@PathVariable(name = "rentalId") long rentalId) {
        return rentalService.delete(rentalId);
    }
}
