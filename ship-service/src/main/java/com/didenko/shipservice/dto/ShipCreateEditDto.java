package com.didenko.shipservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ShipCreateEditDto {

    private String id;

    private String name;

    private String previousName;

    private List<SeatCreateEditDto> seats;

}
