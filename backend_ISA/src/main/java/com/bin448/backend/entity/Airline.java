package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "airline")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airline", cascade = CascadeType.ALL)
    private Collection<PlaneSeat> planeSeats;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "airline_luggage_price", cascade = CascadeType.ALL)
    private Collection<LuggagePrice> luggagePrice;


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

    public Collection<PlaneSeat> getPlaneSeats() {
        return planeSeats;
    }

    public void setPlaneSeats(Collection<PlaneSeat> planeSeats) {
        this.planeSeats = planeSeats;
    }
}
