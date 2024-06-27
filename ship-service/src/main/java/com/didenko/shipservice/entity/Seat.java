package com.didenko.shipservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "ship")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "seat_group")
    private Integer seatGroup;

    private Integer number;

    @Column(name = "number_of_persons")
    private Integer numberOfPersons;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private SeatVacancy vacancy;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_class")
    private SeatClass seatClass;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ship ship;

    public boolean isVacant(){
        return vacancy.equals(SeatVacancy.VACANT);
    }

    /**
     * used to compare seats of the same ship for ship update
     * compares only by seat group and seat number
     */
    public boolean compareToSameShipSeat(Seat seat){
        return this.seatGroup.equals(seat.getSeatGroup()) && this.number.equals(seat.getNumber());
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        ship.addSeat(this);
    }
}