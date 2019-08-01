package com.bin448.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "carpricelist",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class CarServicePriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarServicePriceList;
    private Double price;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "carServiceId")
    private CarService carS;


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
