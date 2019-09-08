package com.bin448.backend.converter;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.entity.PlaneTicket;

public abstract class PlaneSeatConverter extends AbstractConverter {

    public static PlaneSeatDTO fromEntity(PlaneSeat e) {
        PlaneSeatDTO planeSeatDTO = new PlaneSeatDTO();
        planeSeatDTO.setId(e.getSeatId());
        planeSeatDTO.setReserved(e.getReserved());
        planeSeatDTO.setAirlineID(e.getAirline().getId());
        planeSeatDTO.setTicketID(e.getTicket().getId());
        return planeSeatDTO;
    }

    public static PlaneSeat toEntity(PlaneSeatDTO d) {
        PlaneSeat planeSeat = new PlaneSeat();
        planeSeat.setSeatId(d.getId());
        planeSeat.setReserved(d.isReserved());
        Airline airline = new Airline();
        airline.setId(d.getAirlineID());
        planeSeat.setAirline(airline);
        PlaneTicket planeTicket = new PlaneTicket();
        planeTicket.setId(d.getTicketID());
        planeSeat.setTicket(planeTicket);
        return planeSeat;
    }
}
