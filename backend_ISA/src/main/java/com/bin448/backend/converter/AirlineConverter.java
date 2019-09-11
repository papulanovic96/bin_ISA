package com.bin448.backend.converter;

import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.repository.PlaneTicketRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AirlineConverter extends AbstractConverter {


    public static AirlineDTO fromEntity(Airline e) {
        AirlineDTO airlineDTO = new AirlineDTO();
        airlineDTO.setId(e.getId());
        airlineDTO.setAddress(e.getAddress());
        airlineDTO.setDescription(e.getDescription());
        airlineDTO.setName(e.getName());
        airlineDTO.setOfficeDestination(e.getOfficeDestination());

        Collection<Flight> flightsSet = e.getFlightList();
        List<Long> flightsID = new ArrayList<>();
        for (Flight f : flightsSet) {
            flightsID.add(f.getId());
        }
        airlineDTO.setFlightListID(flightsID);

        Collection<PlaneTicket> planeTickets = e.getDicountTicket();
        List<Long> ticketsID = new ArrayList<>();
        for (PlaneTicket pt : planeTickets) {
            if(pt.isDiscount()) {
                ticketsID.add(pt.getId());
            }
        }
        airlineDTO.setDiscountTicketListID(ticketsID);

        Collection<Flight> flights = e.getFlightList();
        List<Long> fIds = new ArrayList<>();
        for (Flight ps : flights) {
            fIds.add(ps.getId());
        }
        airlineDTO.setFlightListID(fIds);


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
        if (flightsID != null) {
            for (Long key : flightsID) {
                newFlight.setId(key);
                flights.add(newFlight);
            }
        }
        airline.setFlightList(flights);

        Collection<PlaneTicket> tickets = null;
        List<Long> ticketsID = d.getDiscountTicketListID();
        PlaneTicket newTicket = new PlaneTicket();
        if (ticketsID != null) {
            for (Long key : ticketsID) {
                newTicket.setId(key);
                tickets.add(newTicket);
            }
        }
        airline.setDicountTicket(tickets);

        Collection<Flight> flights1 = null;
        List<Long> flLongList = d.getFlightListID();
        Flight flight2 = new Flight();
        if (flLongList != null) {
            for (Long key : flLongList) {
                flight2.setId(key);
                flights1.add(flight2);
            }
        }
        airline.setFlightList(flights1);

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
