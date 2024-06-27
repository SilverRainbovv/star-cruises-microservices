package com.didenko.discoveryservice;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationFilter
        extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return null;
    }

    public static class Config {

    }

}
