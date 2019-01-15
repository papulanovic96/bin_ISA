package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "flight")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private String odrediste;
    @Column
    private String destinacija;
    @Column
    private Double cena;

}
