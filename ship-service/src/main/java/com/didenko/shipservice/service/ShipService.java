package com.didenko.shipservice.service;

import com.didenko.shipservice.dto.ShipCreateEditDto;
import com.didenko.shipservice.dto.ShipReadDto;

import java.util.List;
import java.util.Optional;

public interface ShipService {

    List<ShipReadDto> findAllShips();

    Optional<String> findShipById(Long id);

    ShipReadDto save(ShipCreateEditDto shipCreateEditDto);
}
