package com.didenko.shipservice.controller;

import com.didenko.shipservice.dto.ShipCreateEditDto;
import com.didenko.shipservice.dto.ShipReadDto;
import com.didenko.shipservice.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("/status/check")
    @GetMapping
    public ResponseEntity<HttpStatus> getServiceStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}