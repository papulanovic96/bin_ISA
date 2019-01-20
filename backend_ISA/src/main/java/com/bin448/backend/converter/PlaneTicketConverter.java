package com.bin448.backend.converter;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.PlaneTicketDTO;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.entity.PlaneTicket;

public abstract class PlaneTicketConverter extends AbstractConverter{

    public static PlaneTicketDTO fromEntity(PlaneTicket e) {
        PlaneTicketDTO planeTicketDTO = new PlaneTicketDTO();
        planeTicketDTO.setId(e.getId());
        if(e.getAirline().getId() != null) {
            planeTicketDTO.setAirlineID(e.getAirline().getId());
        } else {
            planeTicketDTO.setAirlineID(null);
        }
        if(e.getSeat().getSeatId() != null) {
            planeTicketDTO.setPlaneSeatID(e.getSeat().getSeatId());
        } else
        {
            planeTicketDTO.setPlaneSeatID(null);
        }
        return planeTicketDTO;
    }

    public static PlaneTicket toEntity(PlaneTicketDTO d) {
        PlaneTicket planeTicket = new PlaneTicket();
        planeTicket.setId(d.getId());
        Airline airline = new Airline();
        if(d.getAirlineID() != null) {
            airline.setId(d.getAirlineID());
            planeTicket.setAirline(airline);
        } else {
            airline.setId(null); // ni ne treba dupl code
            planeTicket.setAirline(null);
        }
        PlaneSeat planeSeat = new PlaneSeat();
        if(d.getPlaneSeatID() != null) {
            planeSeat.setSeatId(d.getPlaneSeatID());
            planeTicket.setSeat(planeSeat);
        } else
        {
            planeTicket.setSeat(null);
        }
        return planeTicket;
    }
}
