package com.didenko.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @MapsId
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String firstname;

    private String lastname;

    private LocalDate birthdate;

}