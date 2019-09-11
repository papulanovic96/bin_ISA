package com.bin448.backend.converter;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.Flight;

public abstract class FlightConverter extends AbstractConverter{

    public static FlightDTO fromEntity(Flight e) {
        FlightDTO newFlight = new FlightDTO();
        newFlight.setId(e.getId());
        newFlight.setFromDest(e.getFromDestination());
        newFlight.setToDest(e.getToDestination());
        newFlight.setDateAndTimeTakeOff(e.getDateAndTimeTakeOff());
        newFlight.setDateAndTimeLanding(e.getDateAndTimeLanding());
        newFlight.setFlightTime(e.getFlightTime());
        newFlight.setFlightTravelTime(e.getFlightTravelTime());
        newFlight.setTicketPrice(e.getTicketPrice());
        newFlight.setTransferLocation(e.getTransferLocation());
        newFlight.setTransferNumber(e.getTransferNumber());
        newFlight.setAirline(e.getAirline().getName());
        return newFlight;
    }

    public static Flight toEntity(FlightDTO d) {
        Flight newFlight = new Flight();
        newFlight.setId(d.getId());
        newFlight.setFromDestination(d.getFromDest());
        newFlight.setToDestination(d.getToDest());
        newFlight.setDateAndTimeTakeOff(d.getDateAndTimeTakeOff());
        newFlight.setDateAndTimeLanding(d.getDateAndTimeLanding());
        newFlight.setTicketPrice(d.getTicketPrice());
        newFlight.setTransferLocation(d.getTransferLocation());
        newFlight.setFlightTime(d.getFlightTime());
        newFlight.setFlightTravelTime(d.getFlightTravelTime());
        newFlight.setTransferNumber(d.getTransferNumber());
        Airline newAirline = new Airline();
        newAirline.setId(d.getId());
        newFlight.setAirline(newAirline);
        return newFlight;
    }
}
