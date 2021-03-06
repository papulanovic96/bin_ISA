package com.bin448.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "seats")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlaneSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long seatId;
    @Column
    private boolean reserved;
    @ManyToOne
    private Airline airline;
    @ManyToOne
    private PlaneTicket ticket;
    @OneToOne
    private User user;
    @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL)
    private Invitation invitation;

    public PlaneSeat(){}

    public PlaneSeat(Long id){
        this.seatId = id;
    }

    public PlaneSeat(boolean reserved, Airline airline, PlaneTicket ticket) {
        this.reserved = reserved;
        this.airline = airline;
        this.ticket = ticket;
    }

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

    public boolean getReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }
}
