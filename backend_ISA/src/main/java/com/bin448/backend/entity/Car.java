package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity

@Table(name = "cars",uniqueConstraints={@UniqueConstraint(columnNames={"regID"})})

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long carId;
    @Column
    private boolean reserved;
    @Column(name = "regID",nullable = false)
    private String regID;
    @Column
    private Double avgGrade;
    @Column
    private String model;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CarService.class)
    @JoinColumn(name = "idService", nullable = false)
    private CarService carService;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }


    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public String getRegID() {
        return regID;
    }

    public void setRegID(String regID) {
        this.regID = regID;
    }


    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
