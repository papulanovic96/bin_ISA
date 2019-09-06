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

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;



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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
