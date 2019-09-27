package com.bin448.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "invitation")
public class Invitation {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @ManyToOne
    private User inviting;
    @ManyToOne
    private User receiving;
    @OneToOne
    private PlaneSeat seat;
    @Column
    private boolean invitationStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getInviting() {
        return inviting;
    }

    public void setInviting(User inviting) {
        this.inviting = inviting;
    }

    public User getReceiving() {
        return receiving;
    }

    public void setReceiving(User receiving) {
        this.receiving = receiving;
    }

    public PlaneSeat getSeat() {
        return seat;
    }

    public void setSeat(PlaneSeat seat) {
        this.seat = seat;
    }

    public boolean isInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(boolean invitationStatus) {
        this.invitationStatus = invitationStatus;
    }
}
