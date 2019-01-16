package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PlaneTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ticket",cascade = CascadeType.MERGE)
    private PlaneSeat seat;
    @JsonManagedReference
    @ManyToOne
    private Airline airline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaneSeat getSeat() {
        return seat;
    }

    public void setSeat(PlaneSeat seat) {
        this.seat = seat;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
