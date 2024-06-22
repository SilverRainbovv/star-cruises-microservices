package com.didenko.userservice.mapper;

import com.didenko.userservice.dto.ClientCreateEditDto;
import com.didenko.userservice.entity.Client;
import com.didenko.userservice.entity.Role;
import com.didenko.userservice.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientCreateEditDtoMapper implements Mapper<ClientCreateEditDto, Client> {

    @Override
    public Client map(ClientCreateEditDto from) {

        User user = User.builder()
                .UUID(UUID.randomUUID().toString())
                .role(Role.CLIENT)
                .email(from.getEmail())
                .password(from.getPassword())
                .build();

        Client client = Client.builder()
                .firstname(from.getFirstname())
                .lastname(from.getLastname())
                .birthdate(from.getBirthdate())
                .build();
        client.setUser(user);

        return client;
    }
}
