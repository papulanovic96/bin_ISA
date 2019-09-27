package com.bin448.backend.entity.DTOentity;

public class PlaneSeatDTO {
    private Long seatId;
    private boolean reserved;
    private Long airline;
    private Long ticket;
    private String username;

    public PlaneSeatDTO() {
        super();
    }

    public PlaneSeatDTO(Long seatId, boolean reserved, Long airline, Long ticket, String username) {
        this.seatId = seatId;
        this.reserved = reserved;
        this.airline = airline;
        this.ticket = ticket;
        this.username = username;
    }

    public Long getId() {
        return seatId;
    }

    public void setId(Long id) {
        this.seatId = id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Long getAirlineID() {
        return airline;
    }

    public void setAirlineID(Long airlineID) {
        this.airline = airlineID;
    }

    public Long getTicketID() {
        return ticket;
    }

    public void setTicketID(Long ticketID) {
        this.ticket = ticketID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
