package com.transdev.prestation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private Date journeyDate;

    @ManyToMany
    @JoinColumn(name = "bus_id")
    private List<Bus> buses;

    @ManyToOne
    @JoinColumn(name = "id")
    private Client client;
}
