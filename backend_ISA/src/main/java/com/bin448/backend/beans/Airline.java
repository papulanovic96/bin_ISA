package com.bin448.backend.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "airline")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String description;
    @Column
    private String office;

    @ManyToMany
    private List<Flight> letovi;
    //private List<String> ticket;
    //private List<String> sedista;
    //private HashMap<String, Double> cenovnikPrtljaga;


}
