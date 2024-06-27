package com.didenko.shipservice.mapper;

import com.didenko.shipservice.dto.SeatCreateEditDto;
import com.didenko.shipservice.dto.ShipCreateEditDto;
import com.didenko.shipservice.entity.Seat;
import com.didenko.shipservice.entity.Ship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ShipCreateEditDtoMapper implements Mapper<ShipCreateEditDto, Ship> {

    private final SeatCreateEditDtoMapper seatCreateEditDtoMapper;


    @Override
    public Ship mapFrom(ShipCreateEditDto shipDto) {
        Ship ship = Ship.builder()
                .name(shipDto.getName())
                .build();

        List<SeatCreateEditDto> seatDtos = shipDto.getSeats();

        seatDtos.forEach(seatDto -> createSeatsGroup(seatDto, ship));
        return ship;
    }

    private void createSeatsGroup(SeatCreateEditDto seatDto, Ship ship){
        for (int i = seatDto.getFirstSeatNumber(); i <= seatDto.getLastSeatNumber(); i++){
            Seat seat = seatCreateEditDtoMapper.mapFrom(seatDto, i);
            seat.setShip(ship);
        }
    }
}
