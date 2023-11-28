package com.example.carrental.mapper;

import com.example.carrental.dto.RefundDto;
import com.example.carrental.entity.Refund;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RefundMapper {

    private EmployeeMapper employeeMapper;
    private ReservationMapper reservationMapper;

    public Refund mapToEntity(RefundDto refundDto) {
        Refund refund = new Refund();
        refund.setId(refundDto.getId());
        refund.setComments(refundDto.getComments());
        refund.setReturnDate(refundDto.getReturnDate());
        refund.setComments(refundDto.getComments());

        return refund;
    }

    public RefundDto mapToDto(Refund refund) {
        RefundDto refundDto = new RefundDto();
        refundDto.setId(refund.getId());
        refundDto.setComments(refund.getComments());
        refundDto.setSurcharge(refund.getSurcharge());
        refundDto.setReturnDate(refund.getReturnDate());
        refundDto.setEmployeeId(refund.getEmployee().getId());
        refundDto.setEmployeeDto(employeeMapper.mapToDto(refund.getEmployee()));
        refundDto.setReservationId(refund.getReservation().getReservation_id());
        refundDto.setReservationDto(reservationMapper.mapToDto(refund.getReservation()));

        return refundDto;
    }
}
