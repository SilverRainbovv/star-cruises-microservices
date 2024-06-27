package com.didenko.shipservice.mapper;

import com.didenko.shipservice.dto.SeatCreateEditDto;
import com.didenko.shipservice.entity.Seat;
import com.didenko.shipservice.entity.SeatClass;
import com.didenko.shipservice.entity.SeatVacancy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SeatCreateEditDtoMapper {

    public Seat mapFrom(SeatCreateEditDto seatDto, int seatNumber) {
        return  Seat.builder()
                .seatGroup(Integer.valueOf(seatDto.getSeatGroup()))
                .number(seatNumber)
                .price(new BigDecimal(seatDto.getPrice()))
                .vacancy(SeatVacancy.VACANT)
                .seatClass(SeatClass.valueOf(seatDto.getSeatClass()))
                .numberOfPersons(Integer.valueOf(seatDto.getNumberOfPersons()))
                .build();
    }
}
