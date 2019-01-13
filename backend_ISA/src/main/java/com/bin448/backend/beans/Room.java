package com.bin448.backend.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "room")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long number;
    @ManyToOne
    private Hotel hotel;
    @Column
    private Double pricePerNight;
    private Double avgGrade;

}
