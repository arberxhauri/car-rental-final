package com.example.carrental.service;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.entity.Rental;
import com.example.carrental.mapper.RentalMapper;
import com.example.carrental.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalService {

    private RentalRepository rentalRepository;
    private RentalMapper rentalMapper;

    public RentalDto save(RentalDto rentalDto) {
        Rental savedRental = rentalRepository.save(rentalMapper.mapToEntity(rentalDto));
        return rentalMapper.mapToDto(savedRental);
    }

    public List<RentalDto> findAll() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream().map(rental -> rentalMapper.mapToDto(rental)).collect(Collectors.toList());
    }

    public RentalDto findById(long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() ->
                new RuntimeException("Rental with id: " + rentalId + " was not found!"));
        return rentalMapper.mapToDto(rental);
    }

    public RentalDto update(RentalDto rentalDto, long rentalId){
        Rental existingRental = rentalRepository.findById(rentalId).orElseThrow(() ->
                new RuntimeException("Rental with id " + rentalId + " was not found"));
        existingRental.setId(rentalId);
        existingRental.setName(rentalDto.getName());
        existingRental.setOwner(rentalDto.getOwner());
        existingRental.setContactAddress(rentalDto.getContactAddress());

        Rental savedRental = rentalRepository.save(existingRental);

        return rentalMapper.mapToDto(savedRental);
    }

    public String delete(long rentalId){
        Rental existingRental = rentalRepository.findById(rentalId).orElseThrow(() ->
                new RuntimeException("Rental with id " + rentalId + " was not found"));
        rentalRepository.delete(existingRental);

        return "Rental with id " + rentalId + " was deleted successfully";
    }
}
