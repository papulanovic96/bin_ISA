package com.bin448.backend.converter;

import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.AirlineDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AirlineConverter extends AbstractConverter{

    public static AirlineDTO fromEntity(Airline e) {
        AirlineDTO airlineDTO = new AirlineDTO();
        airlineDTO.setId(e.getId());
        airlineDTO.setAddress(e.getAddress());
        airlineDTO.setDescription(e.getDescription());
        airlineDTO.setName(e.getName());
        airlineDTO.setOfficeDestination(e.getOfficeDestination());

        Collection<Flight> flightsSet = e.getFlightList();
        List<Long> flightsID = new ArrayList<>();
        for(Flight f : flightsSet) {
            flightsID.add(f.getId());
        }
        airlineDTO.setFlightListID(flightsID);

        Collection<PlaneTicket> planeTickets = e.getDicountTicket();
        List<Long> ticketsID = new ArrayList<>();
        for(PlaneTicket pt : planeTickets) {
            ticketsID.add(pt.getId());
        }
        airlineDTO.setDiscountTicketListID(ticketsID);

        Collection<PlaneSeat> planeSeats = e.getPlaneSeats();
        List<Long> seatsID = new ArrayList<>();
        for(PlaneSeat ps : planeSeats) {
            seatsID.add(ps.getSeatId());
        }
        airlineDTO.setSeatsListID(seatsID);

        Collection<LuggagePrice> luggagePrices = e.getLuggagePrice();
        List<Long> luggageID = new ArrayList<>();
        for(LuggagePrice lg : luggagePrices) {
            luggageID.add(lg.getId());
        }
        airlineDTO.setLuggageID(luggageID);

    return airlineDTO;
    }

    public static Airline toEntity(AirlineDTO d) {
        Airline airline = new Airline();
        airline.setId(d.getId());
        airline.setAddress(d.getAddress());
        airline.setDescription(d.getDescription());
        airline.setName(d.getName());

        Collection<Flight> flights = null;
        List<Long> flightsID = d.getFlightListID();
        Flight newFlight = new Flight();
        if(flightsID != null) {
            for(Long key : flightsID) {
                newFlight.setId(key);
                flights.add(newFlight);
            }
        }
        airline.setFlightList(flights);

        Collection<PlaneTicket> tickets = null;
        List<Long> ticketsID = d.getDiscountTicketListID();
        PlaneTicket newTicket = new PlaneTicket();
        if(ticketsID != null) {
            for(Long key : ticketsID) {
                newTicket.setId(key);
                tickets.add(newTicket);
            }
        }
        airline.setDicountTicket(tickets);

        Collection<PlaneSeat> seats = null;
        List<Long> seatsID = d.getSeatsListID();
        PlaneSeat newSeat = new PlaneSeat();
        if(seatsID != null) {
            for(Long key : seatsID) {
                newSeat.setSeatId(key);
                seats.add(newSeat);
            }
        }
        airline.setPlaneSeats(seats);

        Collection<LuggagePrice> luggagePrices = null;
        List<Long> luggageID = d.getLuggageID();
        LuggagePrice newLuggage = new LuggagePrice();
        if(luggageID != null) {
            for(Long key : luggageID) {
                newLuggage.setId(key);
                luggagePrices.add(newLuggage);
            }
        }
        airline.setLuggagePrice(luggagePrices);
        return airline;
    }
}
