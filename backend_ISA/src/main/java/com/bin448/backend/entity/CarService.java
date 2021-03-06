package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "carService", uniqueConstraints = {@UniqueConstraint(columnNames = {"carServiceName"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long carServiceId;

    private Boolean deleted;

    @Column(name="carServiceName")
    private String carServiceName;
    @Column
    private String carServiceAddress;
    @Column
    private String carServiceDescription;
    @Column
    private String carServiceLocation;
    @Column
    private Double avgGrade;
    @Column
    private Double profit;

    public Long getCarService_id() {
        return carServiceId;
    }

    public void setCarService_id(Long carServiceId) {
        this.carServiceId = carServiceId;
    }

    public String getCarServiceName() {
        return carServiceName;
    }

    public void setCarServiceName(String carServiceName) {
        this.carServiceName = carServiceName;
    }

    public String getCarServiceAddress() {
        return carServiceAddress;
    }

    public void setCarServiceAddress(String carServiceAddress) {
        this.carServiceAddress = carServiceAddress;
    }

    public String getCarServiceDescription() {
        return carServiceDescription;
    }

    public void setCarServiceDescription(String carServiceDescription) {
        this.carServiceDescription = carServiceDescription;
    }


    public String getCarServiceLocation() {
        return carServiceLocation;
    }

    public void setCarServiceLocation(String carServiceLocation) {
        this.carServiceLocation = carServiceLocation;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
