package com.example.carrental.mapper;

import com.example.carrental.dto.BillDto;
import com.example.carrental.dto.RefundDto;
import com.example.carrental.entity.Bill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BillMapper {

    private ReservationMapper reservationMapper;
    private RefundMapper refundMapper;

    public Bill mapToEntity(BillDto billDto){
        Bill bill = new Bill();
        bill.setId(billDto.getId());
        bill.setAmount(billDto.getAmount());

        return bill;
    }

    public BillDto mapToDto(Bill bill){
        BillDto billDto = new BillDto();
        billDto.setId(bill.getId());
        billDto.setReservationId(bill.getReservation().getReservation_id());
        billDto.setReservationDto(reservationMapper.mapToDto(bill.getReservation()));
        billDto.setAmount(bill.getAmount());

        return billDto;
    }
}
