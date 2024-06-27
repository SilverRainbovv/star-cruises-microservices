package com.didenko.shipservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SeatCreateEditDto {

    private String id;

    private String seatGroup;

    private Integer firstSeatNumber;

    private Integer lastSeatNumber;

    private String numberOfPersons;

    private String price;

    private String seatClass;

}