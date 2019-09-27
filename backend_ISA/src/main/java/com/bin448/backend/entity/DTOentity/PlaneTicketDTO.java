package com.bin448.backend.entity.DTOentity;

import java.util.List;

public class PlaneTicketDTO {

    private Long id;
    private boolean discount;
    private Long userNumber;
    private String tripType;
    private String tripClass;
    private String bag;
    private List<Long> seat;
    private Long flight;
    private String reservedBy;
    private Long airline;

    public PlaneTicketDTO() {

    }

    public PlaneTicketDTO(Long id, boolean discount, Long userNumber, String tripType, String tripClass, String bag, List<Long> seat, Long flight, String reservedBy, Long airline) {
        this.id = id;
        this.discount = discount;
        this.userNumber = userNumber;
        this.tripType = tripType;
        this.tripClass = tripClass;
        this.bag = bag;
        this.seat = seat;
        this.flight = flight;
        this.reservedBy = reservedBy;
        this.airline = airline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getPlaneSeatID() {
        return seat;
    }

    public void setPlaneSeatID(List<Long> planeSeatID) {
        this.seat = planeSeatID;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public Long getFlight() {
        return flight;
    }

    public void setFlight(Long flight) {
        this.flight = flight;
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

    public Long getAirline() {
        return airline;
    }

    public void setAirline(Long airline) {
        this.airline = airline;
    }
}
