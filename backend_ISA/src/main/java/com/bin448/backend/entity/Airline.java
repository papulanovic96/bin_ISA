package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;

@Entity
@Table(name = "airline")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String description;
    @Column
    private String officeDestination;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airline", cascade = CascadeType.ALL)
    private Collection<Flight> flightList;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airline", cascade = CascadeType.ALL)
    private Collection<PlaneTicket> discountTicket;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airline_luggage_price", cascade = CascadeType.ALL)
    private Collection<LuggagePrice> luggagePrice;
    @Version
    private Integer version;

    public Airline(){}

    public Airline(Long id){
        this.id = id;
    }

    public Airline(String name, String address, String description, String officeDestination, Collection<Flight> flightList, Collection<PlaneTicket> discountTicket, Collection<LuggagePrice> luggagePrice) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.officeDestination = officeDestination;
        this.flightList = flightList;
        this.discountTicket = discountTicket;
        this.luggagePrice = luggagePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfficeDestination() {
        return officeDestination;
    }

    public void setOfficeDestination(String officeDestination) {
        this.officeDestination = officeDestination;
    }

    public Collection<PlaneTicket> getDicountTicket() {
        return discountTicket;
    }

    public void setDicountTicket(Collection<PlaneTicket> dicountTicket) {
        this.discountTicket = dicountTicket;
    }

    public Collection<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(Collection<Flight> flightList) {
        this.flightList = flightList;
    }

    public Collection<LuggagePrice> getLuggagePrice() {
        return luggagePrice;
    }

    public void setLuggagePrice(Collection<LuggagePrice> luggagePrice) {
        this.luggagePrice = luggagePrice;
    }

}
