package com.bin448.backend.entity.DTOentity;

public class PlaneTicketDTO {

    private Long id;
    private Long seat;
    private Long airline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlaneSeatID() {
        return seat;
    }

    public void setPlaneSeatID(Long planeSeatID) {
        this.seat = planeSeatID;
    }

    public Long getAirlineID() {
        return airline;
    }

    public void setAirlineID(Long airlineLong) {
        this.airline = airlineLong;
    }
}
