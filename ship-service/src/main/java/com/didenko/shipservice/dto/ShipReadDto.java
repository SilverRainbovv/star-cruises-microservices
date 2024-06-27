package com.didenko.shipservice.dto;

import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ShipReadDto {

    private String id;

    private String name;

    private String freeSeatsRemaining;

    private String startingPrice;

}