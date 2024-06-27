package com.didenko.shipservice.mapper;

public interface Mapper <F, T>{

    T mapFrom(F from);

}
