package com.bin448.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class CarServiceRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "carServiceId")
    private CarService carService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }
}
