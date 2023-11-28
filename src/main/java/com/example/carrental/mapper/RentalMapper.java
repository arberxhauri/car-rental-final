package com.example.carrental.mapper;

import com.example.carrental.dto.RentalDto;
import com.example.carrental.entity.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RentalMapper {

    public Rental mapToEntity(RentalDto rentalDto) {
        Rental rental = new Rental();

        rental.setId(rentalDto.getId());
        rental.setName(rentalDto.getName());
        rental.setOwner(rentalDto.getOwner());
        rental.setContactAddress(rentalDto.getContactAddress());

        return rental;
    }

    public RentalDto mapToDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();

        rentalDto.setId(rental.getId());
        rentalDto.setName(rental.getName());
        rentalDto.setOwner(rental.getOwner());
        rentalDto.setContactAddress(rental.getContactAddress());
        return rentalDto;
    }
}
