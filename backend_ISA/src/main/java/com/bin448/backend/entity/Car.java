package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@Table(name = "cars",uniqueConstraints={@UniqueConstraint(columnNames={"regID"})})

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long carId;
    @Column
    private Boolean reserved;
    @Column(name = "regID",nullable = false)
    private String regID;
    @Column
    private Double avgGrade;
    @Column
    private String model;
    private Integer year;
    private String typeC;
    private Boolean convertible;


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


    public Boolean isReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return typeC;
    }

    public void setType(String type) {
        this.typeC = type;
    }

    public Boolean isConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }


}
