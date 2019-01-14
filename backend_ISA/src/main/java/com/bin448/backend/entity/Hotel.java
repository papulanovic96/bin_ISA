package com.bin448.backend.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;

@Entity
@Table(name = "hotel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long hotel_id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String description;
    @Column
    private HashMap<String, Double> menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.MERGE)
    private Collection<Room> configuration;

    private Double avgGrade;
    private Boolean TV;
    private Boolean WiFi;
    private Boolean roomService;
    private Boolean restorant;
    private Boolean pool;
}
