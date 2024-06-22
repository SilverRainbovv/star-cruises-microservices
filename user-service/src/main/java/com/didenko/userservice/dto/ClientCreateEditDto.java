package com.didenko.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class ClientCreateEditDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @Size(min = 3, max = 64)
    @NotBlank
    private String firstname;

    @Size(min = 3, max = 64)
    @NotBlank
    private String lastname;

    private LocalDate birthdate;
}