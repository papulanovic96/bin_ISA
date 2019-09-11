package com.bin448.backend.converter;

import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.PlaneTicketDTO;

import java.util.ArrayList;
import java.util.List;

public abstract class PlaneTicketConverter extends AbstractConverter {

    public static PlaneTicketDTO fromEntity(PlaneTicket e) {
        PlaneTicketDTO planeTicketDTO = new PlaneTicketDTO();
        planeTicketDTO.setId(e.getId());
        if (e.getFlight().getId() != null) {
            planeTicketDTO.setFlight(e.getFlight().getId());
        } else {
            planeTicketDTO.setFlight(null);
        }
        planeTicketDTO.setDiscount(e.isDiscount());
        planeTicketDTO.setReservedBy(e.getReservedBy().getUsername());
        List<Long> listaSeats = new ArrayList<>();
        List<PlaneSeat> oldlist = e.getSeat();
        for(PlaneSeat ps: oldlist) {
            listaSeats.add(ps.getSeatId());
        }
        planeTicketDTO.setPlaneSeatID(listaSeats);
        planeTicketDTO.setUserNumber(e.getUserNumber());
        planeTicketDTO.setTripType(e.getTripType());
        planeTicketDTO.setTripClass(e.getTripClass());
        planeTicketDTO.setBag(e.getBag());
        planeTicketDTO.setAirline(e.getAirline().getId());
        return planeTicketDTO;
    }

    public static PlaneTicket toEntity(PlaneTicketDTO d) {
        PlaneTicket planeTicket = new PlaneTicket();
        planeTicket.setId(d.getId());

        Flight flight = new Flight();
        if (d.getFlight() != null) {
            flight.setId(d.getFlight());
            planeTicket.setFlight(flight);
        } else {
            flight.setId(null); // ni ne treba dupl code
            planeTicket.setFlight(null);
        }

        planeTicket.setDiscount(d.isDiscount());

        User newUser = new User();
        newUser.setUsername(d.getReservedBy());
        planeTicket.setReservedBy(newUser);

        List<Long> oldList = d.getPlaneSeatID();
        List<PlaneSeat> newList = new ArrayList<>();
        PlaneSeat ps = new PlaneSeat();
        for(Long key: oldList){
            ps.setSeatId(key);
            newList.add(ps);
        }
        planeTicket.setSeat(newList);

        planeTicket.setUserNumber(d.getUserNumber());
        planeTicket.setTripType(d.getTripType());
        planeTicket.setTripClass(d.getTripClass());
        planeTicket.setBag(d.getBag());
        Airline airline = new Airline();
        airline.setId(d.getAirline());
        planeTicket.setAirline(airline);
        return planeTicket;
    }
}
