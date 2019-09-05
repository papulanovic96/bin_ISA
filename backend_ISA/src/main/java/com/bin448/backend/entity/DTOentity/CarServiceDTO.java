package com.bin448.backend.entity.DTOentity;

import com.bin448.backend.entity.Car;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CarServiceDTO {
    private Long id;
    private String carServiceName;
    private String carServiceAddress;
    private String carServiceDescription;
    private String carServiceLocation;
    private Double avgGrade;
    private Boolean deleted;





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

    public Double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(Double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public String getCarServiceLocation() {
        return carServiceLocation;
    }

    public void setCarServiceLocation(String carServiceLocation) {
        this.carServiceLocation = carServiceLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
