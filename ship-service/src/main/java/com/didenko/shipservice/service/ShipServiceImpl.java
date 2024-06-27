package com.didenko.shipservice.service;

import com.didenko.shipservice.dto.ShipCreateEditDto;
import com.didenko.shipservice.dto.ShipReadDto;
import com.didenko.shipservice.entity.Ship;
import com.didenko.shipservice.mapper.ShipCreateEditDtoMapper;
import com.didenko.shipservice.mapper.ShipReadDtoMapper;
import com.didenko.shipservice.repository.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShipServiceImpl implements ShipService{

    private final ShipRepository shipRepository;
    private final ShipCreateEditDtoMapper createEditDoMapper;
    private final ShipReadDtoMapper shipReadDtoMapper;

    @Override
    public List<ShipReadDto> findAllShips() {

        List<Ship> ships = shipRepository.findAll();

        return ships.stream().map(shipReadDtoMapper::mapFrom).toList();
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
