package com.bin448.backend.service;

import com.bin448.backend.converter.AirlineConverter;
import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.AirlineRate;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.repository.AirlineRateRepository;
import com.bin448.backend.repository.AirlineRepository;
import com.bin448.backend.repository.PlaneTicketRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirlineRateRepository airlineRateRepository;
    @Autowired
    private PlaneTicketRepository planeTicketRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AirlineDTO> findAll() {
        List<Airline> oldList = airlineRepository.findAll();
        List<AirlineDTO> newList = AirlineConverter.fromEntityList(oldList, e -> AirlineConverter.fromEntity(e));
        return newList;
    }

    @Override
    public String save(AirlineDTO airline) {
        Airline newAirline = AirlineConverter.toEntity(airline);
        airlineRepository.save(newAirline);
        return "Airline added!";
    }

    @Override
    public String delete(AirlineDTO airline) {
        Airline newAirline = AirlineConverter.toEntity(airline);
        airlineRepository.deleteById(newAirline.getId());
        return "Airline deleted!";
    }

    @Transactional
    @Override
    public boolean modify(AirlineDTO airline) {
        Airline a = AirlineConverter.toEntity(airline);
        if(a == null) {
            return false;
        }
        airlineRepository.modifyAirline(a.getId(), a.getAddress(), a.getDescription(), a.getName(), a.getOfficeDestination());
        return true;
    }

    @Override
    public AirlineDTO findById(Long id) {
        Airline newAirline = airlineRepository.findById(id).orElse(null);
        AirlineDTO newAirlineDTO = AirlineConverter.fromEntity(newAirline);
        return newAirlineDTO;
    }

    @Override
    public Boolean rateAirline(Long userId, Long airlineId, Double rate) {
        AirlineRate airlineRate = new AirlineRate();
        airlineRate.setId(null);
        airlineRate.setAirline(airlineRepository.findById(airlineId).get());
        airlineRate.setUser(userRepository.findById(userId).get());
        airlineRate.setRate(rate);

        List<PlaneTicket> tickets = planeTicketRepository.getAllTicketsForAirline(airlineId,userId);
        List<AirlineRate> airlineRates = airlineRateRepository.findAllByUser_IdAndAirline_Id(userId,airlineId);

        if(tickets.size() != 0 && airlineRates.size() == 0){
            airlineRateRepository.save(airlineRate);
            return  true;
        }
       return false;

    }
}
