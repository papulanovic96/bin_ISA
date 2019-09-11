package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "flight")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private String fromDestination;
    @Column
    private String toDestination;
    @Column
    private String dateAndTimeTakeOff;
    @Column
    private String dateAndTimeLanding;
    @Column
    private String flightTime;
    @Column
    private String flightTravelTime;
    @Column
    private Long transferNumber;
    @Column
    private String transferLocation;
    @Column
    private Double ticketPrice;
    @ManyToOne
    private Airline airline;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "flight", cascade = CascadeType.ALL)
    private PlaneTicket planeTicket;

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

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getFromDestination() {
        return fromDestination;
    }

    public void setFromDestination(String fromDestination) {
        this.fromDestination = fromDestination;
    }

    public String getToDestination() {
        return toDestination;
    }

    public void setToDestination(String toDestination) {
        this.toDestination = toDestination;
    }

    public PlaneTicket getPlaneTicket() {
        return planeTicket;
    }

    public void setPlaneTicket(PlaneTicket planeTicket) {
        this.planeTicket = planeTicket;
    }
}
