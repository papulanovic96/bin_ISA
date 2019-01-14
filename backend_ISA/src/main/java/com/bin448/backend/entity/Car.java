package com.bin448.backend.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Car {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long carId;

    private Double avgGrade;
    @ManyToOne
    private CarService carService;
}
