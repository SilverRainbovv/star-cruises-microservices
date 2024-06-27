package com.didenko.shipservice.mapper;

import com.didenko.shipservice.dto.ShipReadDto;
import com.didenko.shipservice.entity.Seat;
import com.didenko.shipservice.entity.Ship;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ShipReadDtoMapper implements Mapper<Ship, ShipReadDto> {
    @Override
    public ShipReadDto mapFrom(Ship ship) {
        return ShipReadDto.builder()
                .id(ship.getId().toString())
                .name(ship.getName())
                .freeSeatsRemaining(String.valueOf(ship.getSeats()
                        .stream()
                        .filter(Seat::isVacant)
                        .count()))
                .startingPrice(String.valueOf(ship.getSeats()
                                .stream()
                                .map(Seat::getPrice)
                                .min(BigDecimal::compareTo)))
                .build();
    }

}
