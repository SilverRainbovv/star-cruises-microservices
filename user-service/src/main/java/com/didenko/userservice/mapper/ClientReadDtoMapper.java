package com.didenko.userservice.mapper;

import com.didenko.userservice.dto.ClientReadDto;
import com.didenko.userservice.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientReadDtoMapper implements Mapper<Client, ClientReadDto> {

    @Override
    public ClientReadDto map(Client from) {
        return ClientReadDto.builder()
                .email(from.getUser().getEmail())
                .firstname(from.getFirstname())
                .lastname(from.getLastname())
                .birthdate(from.getBirthdate())
                .build();
    }
}
