package com.didenko.userservice.controller;

import com.didenko.userservice.dto.*;
import com.didenko.userservice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ClientReadDto> createUser(@RequestBody @Validated ClientCreateEditDto clientDto) {

        var result = registrationService.register(clientDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping("/status/check")
    @GetMapping
    public ResponseEntity<HttpStatus> getServiceStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}