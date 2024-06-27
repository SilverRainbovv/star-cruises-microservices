package com.didenko.shipservice.service;

import com.didenko.shipservice.dto.ShipCreateEditDto;
import com.didenko.shipservice.dto.ShipReadDto;
import com.didenko.shipservice.entity.Seat;
import com.didenko.shipservice.entity.Ship;
import com.didenko.shipservice.mapper.ShipCreateEditDtoMapper;
import com.didenko.shipservice.mapper.ShipReadDtoMapper;
import com.didenko.shipservice.repository.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShipServiceImpl implements ShipService{

    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final ShipCreateEditDtoMapper createEditDoMapper;
    private final ShipReadDtoMapper shipReadDtoMapper;

    @Override
    public List<ShipReadDto> findAllShips() {

        List<Ship> ships = shipRepository.findAll();

        TypeMap<Ship, ShipReadDto> shipToShipReadDtoTypeMap = modelMapper.createTypeMap(Ship.class, ShipReadDto.class);
        shipToShipReadDtoTypeMap.addMapping(src -> src.getSeats().stream().filter(Seat::isVacant).count(),
                ShipReadDto::setFreeSeatsRemaining);
        shipToShipReadDtoTypeMap.addMapping(src -> src.getSeats().stream().map(Seat::getPrice).min(BigDecimal::compareTo),
                ShipReadDto::setStartingPrice);

        return ships.stream().map(ship -> modelMapper.map(ship, ShipReadDto.class)).toList();
    }

    @Override
    public Optional<String> findShipById(Long id) {
        return Optional.empty();
    }

    @Override
    public ShipReadDto save(ShipCreateEditDto shipCreateEditDto) {

        Ship ship;
        Optional<Ship> maybeShip = shipRepository.findByName(shipCreateEditDto.getPreviousName());
        Ship updatedShip = createEditDoMapper.mapFrom(shipCreateEditDto);
        if (maybeShip.isEmpty()){
            ship = Ship.builder()
                    .name(updatedShip.getName())
                    .build();
            updatedShip.getSeats().forEach(seat -> seat.setShip(ship));

            return shipReadDtoMapper.mapFrom(shipRepository.save(ship));
        } else {
            ship = maybeShip.get();
            ship.setName(updatedShip.getName());
            return shipReadDtoMapper.mapFrom(shipRepository.save(ship));
        }
    }
}
