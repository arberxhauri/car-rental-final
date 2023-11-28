package com.example.carrental.service;

import com.example.carrental.dto.CarDto;
import com.example.carrental.entity.Branch;
import com.example.carrental.entity.Car;
import com.example.carrental.entity.Category;
import com.example.carrental.mapper.CarMapper;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarService {

    private CarRepository carRepository;
    private CarMapper carMapper;
    private CategoryRepository categoryRepository;
    private BranchRepository branchRepository;

    public CarDto save(CarDto carDto) {
        Category existingCategory = categoryRepository.findById(carDto.getCategoryId()).orElseThrow(() ->
                new RuntimeException("Category with id: " + carDto.getCategoryId() + " was not"));

        Branch existingBranch = branchRepository.findById(carDto.getBranchId()).orElseThrow(() ->
                new RuntimeException("Branch with id " + carDto.getBranchId() + " was not found!"));

        Car car = carMapper.mapToEntity(carDto);
        car.setCategory(existingCategory);
        car.setBranch(existingBranch);

        Car savedCar = carRepository.save(car);

        return carMapper.mapToDto(savedCar);
    }

    public List<CarDto> findAll() {
        List<Car> allCars = carRepository.findAll();

        return allCars.stream().map(car -> carMapper.mapToDto(car)).collect(Collectors.toList());
    }

    public List<CarDto> findByCategoryId(long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category with id: " + categoryId + " was not"));

        List<Car> carList = carRepository.findByCategoryId(categoryId);

        return carList.stream().map(car -> carMapper.mapToDto(car)).collect(Collectors.toList());
    }

    public CarDto findById(long carId) {
        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id: " + carId + " was not found!"));

        return carMapper.mapToDto(existingCar);
    }

    public CarDto updateCar(CarDto carDto, long carId) {
        Category existingCategory = categoryRepository.findById(carDto.getCategoryId()).orElseThrow(() ->
                new RuntimeException("Category with id: " + carDto.getCategoryId() + " was not"));

        Branch existingBranch = branchRepository.findById(carDto.getBranchId()).orElseThrow(() ->
                new RuntimeException("Branch with id " + carDto.getBranchId() + " was not found!"));

        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id: " + carId + " was not found!"));

        Car car = carMapper.mapToEntity(carDto);

        car.setId(carId);
        car.setCategory(existingCategory);
        car.setBranch(existingBranch);

        Car savedCar = carRepository.save(car);

        return carMapper.mapToDto(savedCar);
    }

    public String deleteCar(long carId) {
        Car existingCar = carRepository.findById(carId).orElseThrow(() ->
                new RuntimeException("Car with id: " + carId + " was not found!"));
        carRepository.delete(existingCar);
        return "Car with id" + carId + " was deleted successfully";
    }
}
