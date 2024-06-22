package com.didenko.userservice.service;

import com.didenko.userservice.dto.ClientCreateEditDto;
import com.didenko.userservice.dto.ClientReadDto;
import com.didenko.userservice.entity.Client;
import com.didenko.userservice.mapper.ClientCreateEditDtoMapper;
import com.didenko.userservice.mapper.ClientReadDtoMapper;
import com.didenko.userservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final ClientCreateEditDtoMapper clientCreateEditDtoMapper;
    private final ClientReadDtoMapper clientReadDtoMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ClientRepository clientRepository;

    public ClientReadDto register(ClientCreateEditDto clientDto) {

        clientDto.setPassword(bCryptPasswordEncoder.encode(clientDto.getPassword()));

        Client client = clientCreateEditDtoMapper.map(clientDto);

        client = clientRepository.save(client);

        return clientReadDtoMapper.map(client);
    }
}