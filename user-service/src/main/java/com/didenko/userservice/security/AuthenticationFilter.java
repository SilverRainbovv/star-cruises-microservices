package com.didenko.userservice.security;

import com.didenko.userservice.dto.LoginDto;
import com.didenko.userservice.entity.User;
import com.didenko.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.security.Key;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final Environment env;
    private static final byte[] SECRET = "hkblkh31y2y3c9p1ncy2p12cpyn38yc123c123c12c3".getBytes();
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET);

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                UserService userService, Environment env) {
        super(authenticationManager);
        this.userService = userService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            LoginDto credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.email(),
                            credentials.password(),
                            new ArrayList<>()
                    )
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        protected void successfulAuthentication(HttpServletRequest request,
                                                HttpServletResponse response,
                                                FilterChain chain,
                                                Authentication authResult)
                throws IOException, ServletException {

            String username = ((User)authResult.getPrincipal()).getUsername();
            User userDetails = userService.getUserDetailsByEmail(username);

            String token = Jwts.builder().setSubject(userDetails.getUUID())
                    .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                    .setIssuedAt(Date.from(Instant.now()))
                    .signWith(SECRET_KEY)
                    .compact();

            response.addHeader("token", token);
            response.addHeader("UUID", userDetails.getUUID());
        }

}