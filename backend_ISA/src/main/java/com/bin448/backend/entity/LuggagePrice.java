package com.bin448.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "luggage_price")
public class LuggagePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String luggage;
    @Column
    private String luggage_info;
    @Column
    private Double price;
    @ManyToOne
    private Airline airline_luggage_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage;
    }

    public String getLuggage_info() {
        return luggage_info;
    }

    public void setLuggage_info(String luggage_info) {
        this.luggage_info = luggage_info;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Airline getAirline_luggage_price() {
        return airline_luggage_price;
    }

    public void setAirline_luggage_price(Airline airline_luggage_price) {
        this.airline_luggage_price = airline_luggage_price;
    }

}
