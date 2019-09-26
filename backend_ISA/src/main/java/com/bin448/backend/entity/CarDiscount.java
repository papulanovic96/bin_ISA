package com.bin448.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CarDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;
    private Double price;
    private Double rateOfDiscount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRateOfDiscount() {
        return rateOfDiscount;
    }

    public void setRateOfDiscount(Double rateOfDiscount) {
        this.rateOfDiscount = rateOfDiscount;
    }
}
