package com.didenko.shipservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@EqualsAndHashCode(of = "name")
@ToString(exclude = {"seats"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ship {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ship", fetch = FetchType.LAZY, cascade = {PERSIST, MERGE, REFRESH})
    @Builder.Default
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat){
        this.seats.add(seat);
    }

    public void removeSeats(List<Seat> seats){
        this.seats.removeAll(seats);
    }

}