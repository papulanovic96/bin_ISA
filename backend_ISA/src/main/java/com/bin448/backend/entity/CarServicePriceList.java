package com.bin448.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "carpricelist")
public class CarServicePriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarServicePriceList;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "carServiceId")
    private CarService carS;

    @OneToOne
    @JoinColumn(name = "carId")
    private Car car;



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CarService getCarS() {
        return carS;
    }

    public void setCarS(CarService carS) {
        this.carS = carS;
    }

    public Long getIdCarServicePriceList() {
        return idCarServicePriceList;
    }

    public void setIdCarServicePriceList(Long idCarServicePriceList) {
        this.idCarServicePriceList = idCarServicePriceList;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
