package com.didenko.shipservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class ShipServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }

}
