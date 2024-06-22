package com.didenko.userservice.mapper;

public interface Mapper <F, T>{

    T map(F from);

}
