package com.bin448.backend.converter;

import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.PlaneTicketDTO;
import com.bin448.backend.repository.FlightRepository;
import com.bin448.backend.repository.PlaneTicketRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaneTicketConverter extends AbstractConverter {

    private static UserRepository userRepository;
    private static FlightRepository flightRepository;

    public PlaneTicketConverter(UserRepository ur, FlightRepository fr){

        this.userRepository = ur;
        this.flightRepository = fr;
    }

    public static PlaneTicketDTO fromEntity(PlaneTicket e) {
        PlaneTicketDTO planeTicketDTO = new PlaneTicketDTO();
        planeTicketDTO.setId(e.getId());
        if (e.getFlight()!= null) {
            planeTicketDTO.setFlight(e.getFlight().getId());
        } else {
            planeTicketDTO.setFlight(null);
        }
        planeTicketDTO.setDiscount(e.isDiscount());
        if(e.getReservedBy() != null){
            planeTicketDTO.setReservedBy(e.getReservedBy().getUsername());
        }
        List<Long> listaSeats = new ArrayList<>();
        List<PlaneSeat> oldlist = e.getSeat();
        if(oldlist != null){
            for(PlaneSeat ps: oldlist) {
                listaSeats.add(ps.getSeatId());
            }
        }
        planeTicketDTO.setPlaneSeatID(listaSeats);
        planeTicketDTO.setUserNumber(e.getUserNumber());
        planeTicketDTO.setTripType(e.getTripType());
        planeTicketDTO.setTripClass(e.getTripClass());
        planeTicketDTO.setBag(e.getBag());
        if(e.getAirline() != null){
            planeTicketDTO.setAirline(e.getAirline().getId());
        }
        return planeTicketDTO;
    }

    public static PlaneTicket toEntity(PlaneTicketDTO d) {
        PlaneTicket planeTicket = new PlaneTicket();
        planeTicket.setId(d.getId());

        Flight flight = new Flight();
        if (d.getFlight() != null) {
            flight = flightRepository.getOne(d.getFlight());
            planeTicket.setFlight(flight);
        } else {
            flight.setId(null); // ni ne treba dupl code
            planeTicket.setFlight(null);
        }

        planeTicket.setDiscount(d.isDiscount());
        if(d.getReservedBy() != null) {
            User newUser = userRepository.findByUsername(d.getReservedBy());
            planeTicket.setReservedBy(newUser);
        }

        List<Long> oldList = d.getPlaneSeatID();
        List<PlaneSeat> newList = new ArrayList<>();
        PlaneSeat ps = new PlaneSeat();
        if(oldList != null){
            for(Long key: oldList){
                ps.setSeatId(key);
                newList.add(ps);
            }
        }
        planeTicket.setSeat(newList);

        planeTicket.setUserNumber(d.getUserNumber());
        planeTicket.setTripType(d.getTripType());
        planeTicket.setTripClass(d.getTripClass());
        planeTicket.setBag(d.getBag());
        if(d.getAirline() != null) {
            Airline airline = new Airline();
            airline.setId(d.getAirline());
            planeTicket.setAirline(airline);
        }
        return planeTicket;
    }
}
