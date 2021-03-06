package com.bin448.backend.entity.DTOentity;

import java.util.List;

public class AirlineDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private String officeDestination;
    private List<Long> flightListID;
    private List<Long> discountTicketListID;
    private List<Long> luggageID;

    public AirlineDTO() {
        super();
    }

    public AirlineDTO(Long id, String name, String address, String description, String officeDestination) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.officeDestination = officeDestination;
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

    public List<Long> getFlightListID() {
        return flightListID;
    }

    public void setFlightListID(List<Long> flightListID) {
        this.flightListID = flightListID;
    }

    public List<Long> getDiscountTicketListID() {
        return discountTicketListID;
    }

    public void setDiscountTicketListID(List<Long> discountTicketListID) {
        this.discountTicketListID = discountTicketListID;
    }

    public List<Long> getLuggageID() {
        return luggageID;
    }

    public void setLuggageID(List<Long> luggageID) {
        this.luggageID = luggageID;
    }
}
