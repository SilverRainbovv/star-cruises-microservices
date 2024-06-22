package com.didenko.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientReadDto {

    private String email;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;
}