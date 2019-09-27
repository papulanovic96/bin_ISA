package com.bin448.backend.entity.DTOentity;

public class InvitationDTO {

    private Long id;
    private String userInivte;
    private String userReceive;
    private Long seatId;
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserInivte() {
        return userInivte;
    }

    public void setUserInivte(String userInivte) {
        this.userInivte = userInivte;
    }

    public String getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(String userReceive) {
        this.userReceive = userReceive;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
