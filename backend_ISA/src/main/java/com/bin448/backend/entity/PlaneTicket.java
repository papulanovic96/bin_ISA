package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tickets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlaneTicket {
    @Id
    @Column(nullable = false)
    private Long id;
    @Column
    private boolean discount;
    @Column
    private Long userNumber;
    @Column
    private String tripType;
    @Column
    private String tripClass;
    @Column
    private String bag;
    @OneToOne
    private Flight flight;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<PlaneSeat> seat;
    @ManyToOne
    private User reservedBy;
    @ManyToOne
    private Airline airline;

    public PlaneTicket() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PlaneSeat> getSeat() {
        return seat;
    }

    public void setSeat(List<PlaneSeat> seat) {
        this.seat = seat;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(User reservedBy) {
        this.reservedBy = reservedBy;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getTripClass() {
        return tripClass;
    }

    public void setTripClass(String tripClass) {
        this.tripClass = tripClass;
    }

    public String getBag() {
        return bag;
    }

    public void setBag(String bag) {
        this.bag = bag;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
