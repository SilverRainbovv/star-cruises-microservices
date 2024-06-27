package com.didenko.shipservice.entity;

import lombok.Getter;

@Getter
public enum SeatClass {
    INTERIOR("Interior"), OUTSIDE_VIEW("Outside view"), BALCONY("Balcony"), SUITE("Suite");

    public final String name;

    SeatClass(String name){
        this.name = name;
    }
}