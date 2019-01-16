package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "seats")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PlaneSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long seatId;

    @JsonManagedReference
    @ManyToOne
    private Airline airline;
    @JsonManagedReference
    @OneToOne
    private PlaneTicket ticket;

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public PlaneTicket getTicket() {
        return ticket;
    }

    public void setTicket(PlaneTicket ticket) {
        this.ticket = ticket;
    }
}