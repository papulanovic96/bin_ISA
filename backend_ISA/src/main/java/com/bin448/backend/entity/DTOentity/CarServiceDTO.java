package com.bin448.backend.entity.DTOentity;

import com.bin448.backend.entity.Car;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CarServiceDTO {

    private String carServiceName;
    private String carServiceAddress;
    private String carServiceDescription;
    private String carServiceLocation;
    private List<String> carsCollection;
    private Double avgGrade;
    private HashMap<String, Double> carServiceMenu;




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

    public List<String> getCarsCollection() {
        return carsCollection;
    }

    public void setCarsCollection(List<String> carsCollection) {
        this.carsCollection = carsCollection;
    }

    public HashMap<String, Double> getCarServiceMenu() {
        return carServiceMenu;
    }

    public void setCarServiceMenu(HashMap<String, Double> carServiceMenu) {
        this.carServiceMenu = carServiceMenu;
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
}
