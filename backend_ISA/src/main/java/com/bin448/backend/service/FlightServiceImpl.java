package com.bin448.backend.service;

import com.bin448.backend.converter.FlightConverter;
import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.Flight;
import com.bin448.backend.entity.FlightRate;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.repository.FlightRateRepository;
import com.bin448.backend.repository.FlightRepository;
import com.bin448.backend.repository.PlaneTicketRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlightRateRepository flightRateRepository;
    @Autowired
    private PlaneTicketRepository planeTicketRepository;

    @Override
    public String save(FlightDTO newFlight) {
        Flight nf = FlightConverter.toEntity(newFlight);
        flightRepository.save(nf);
        return "Flight added!";
    }

    @Override
    public String delete(FlightDTO flightDTO) {
        Flight nf = FlightConverter.toEntity(flightDTO);
        flightRepository.delete(nf);
        return "Flight deleted!";
    }

    @Override
    public FlightDTO findById(Long id) {
        Flight nf = flightRepository.findById(id).orElse(null);
        FlightDTO nfDTO = FlightConverter.fromEntity(nf);
        return nfDTO;
    }

    @Override
    public List<Flight> checkIT(String from, String to, String u1, String u2) {
        List<Flight> newList;
        newList = flightRepository.checkAvailability(from, to, u1, u2);
        return newList;
    }

    @Override
    public List<FlightDTO> findAll() {
        List<Flight> list = flightRepository.findAll();
        List<FlightDTO> listDTO = FlightConverter.fromEntityList(list, e -> FlightConverter.fromEntity(e));
        return listDTO;
    }

    @Override
    public Boolean rateFlight(Long userId, Long flightId, Double rate) {
        FlightRate flightRate = new FlightRate();
        flightRate.setId(null);
        flightRate.setRate(rate);
        flightRate.setUser(userRepository.findById(userId).get());
        flightRate.setFlight(flightRepository.findById(flightId).get());

        List<PlaneTicket> tickets = planeTicketRepository.getAllTickets(flightId,userId);
        List<FlightRate> flightRates = flightRateRepository.findAllByUser_IdAndFlight_Id(userId,flightId);

        if(tickets.size()!=0 && flightRates.size() == 0){
            flightRateRepository.save(flightRate);
            return  true;
        }

        return false;
    }
}
