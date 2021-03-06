package com.bin448.backend.entity.DTOentity;

public class FlightDTO {

    private Long id;
    private String fromDest;
    private String toDest;
    private String dateAndTimeTakeOff;
    private String dateAndTimeLanding;
    private String flightTime;
    private String flightTravelTime;
    private Long transferNumber;
    private String transferLocation;
    private Double ticketPrice;
    private Long airline;
    private Long planeTicket;

    public FlightDTO() {

    }

    public FlightDTO(Long id, String fromDest, String toDest, String dateAndTimeTakeOff, String dateAndTimeLanding, String flightTime, String flightTravelTime, Long transferNumber, String transferLocation, Double ticketPrice, Long airline, Long planeTicket) {
        this.id = id;
        this.fromDest = fromDest;
        this.toDest = toDest;
        this.dateAndTimeTakeOff = dateAndTimeTakeOff;
        this.dateAndTimeLanding = dateAndTimeLanding;
        this.flightTime = flightTime;
        this.flightTravelTime = flightTravelTime;
        this.transferNumber = transferNumber;
        this.transferLocation = transferLocation;
        this.ticketPrice = ticketPrice;
        this.airline = airline;
        this.planeTicket = planeTicket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateAndTimeTakeOff() {
        return dateAndTimeTakeOff;
    }

    public void setDateAndTimeTakeOff(String dateAndTimeTakeOff) {
        this.dateAndTimeTakeOff = dateAndTimeTakeOff;
    }

    public String getDateAndTimeLanding() {
        return dateAndTimeLanding;
    }

    public void setDateAndTimeLanding(String dateAndTimeLanding) {
        this.dateAndTimeLanding = dateAndTimeLanding;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getFlightTravelTime() {
        return flightTravelTime;
    }

    public void setFlightTravelTime(String flightTravelTime) {
        this.flightTravelTime = flightTravelTime;
    }

    public Long getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(Long transferNumber) {
        this.transferNumber = transferNumber;
    }

    public String getTransferLocation() {
        return transferLocation;
    }

    public void setTransferLocation(String transferLocation) {
        this.transferLocation = transferLocation;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Long getAirline() {
        return airline;
    }

    public void setAirline(Long airline) {
        this.airline = airline;
    }

    public String getFromDest() {
        return fromDest;
    }

    public void setFromDest(String fromDest) {
        this.fromDest = fromDest;
    }

    public String getToDest() {
        return toDest;
    }

    public void setToDest(String toDest) {
        this.toDest = toDest;
    }

    public Long getPlaneTicket() {
        return planeTicket;
    }

    public void setPlaneTicket(Long planeTicket) {
        this.planeTicket = planeTicket;
    }
}
