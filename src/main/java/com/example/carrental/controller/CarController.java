package com.example.carrental.controller;

import com.example.carrental.dto.CarDto;
import com.example.carrental.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    @PostMapping("/save")
    public CarDto save(@Valid @RequestBody CarDto carDto) {
        return carService.save(carDto);
    }

    @GetMapping("/list")
    public List<CarDto> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public CarDto findById(@PathVariable long id) {
        return carService.findById(id);
    }

    @GetMapping("/findByCategory/{categoryId}")
    public List<CarDto> findByCategoryId(@PathVariable(name = "categoryId") long categoryId) {
        return carService.findByCategoryId(categoryId);
    }

    @PutMapping("/update/{carId}")
    public CarDto updateCar(@RequestBody CarDto carDto,
                            @PathVariable(name = "carId") long carId) {
        return carService.updateCar(carDto, carId);
    }

    @DeleteMapping("/delete/{carId}")
    public String delete(@PathVariable(name = "carId") long carId) {
        return carService.deleteCar(carId);
    }
}
