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

    public Long getCarService_id() {
        return carService_id;
    }

    public void setCarService_id(Long carService_id) {
        this.carService_id = carService_id;
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

    public HashMap<String, Double> getCarServiceMenu() {
        return carServiceMenu;
    }

    public void setCarServiceMenu(HashMap<String, Double> carServiceMenu) {
        this.carServiceMenu = carServiceMenu;
    }

    public Collection<Car> getCarsCollection() {
        return carsCollection;
    }

    public void setCarsCollection(Collection<Car> carsCollection) {
        this.carsCollection = carsCollection;
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
}
