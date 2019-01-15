package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;

@Entity
@Table(name = "carService")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long carService_id;

    @Column
    private String carServiceName;
    @Column
    private String carServiceAddress;
    @Column
    private String carServiceDescription;
    @Column
    private HashMap<String, Double> carServiceMenu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carService", cascade = CascadeType.MERGE)
    private Collection<Car> carsCollection;
    @Column
    private String carServiceLocation;

    private Double avgGrade;
}
