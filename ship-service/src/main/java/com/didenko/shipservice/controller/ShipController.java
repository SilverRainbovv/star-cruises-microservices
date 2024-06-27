package com.didenko.shipservice.controller;

import com.didenko.shipservice.dto.ShipCreateEditDto;
import com.didenko.shipservice.dto.ShipReadDto;
import com.didenko.shipservice.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/ships")
@RestController
public class ShipController {

    private final ShipService shipService;

    @GetMapping
    public List<ShipReadDto> getShips() {

        return shipService.findAllShips();

    }

    @PostMapping
    public ShipReadDto createShip(@RequestBody ShipCreateEditDto shipCreateEditDto) {

        return shipService.save(shipCreateEditDto);

    }


}