package com.bin448.backend.service;

import com.bin448.backend.converter.AirlineConverter;
import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public List<AirlineDTO> findAll() {
        List<Airline> oldList = airlineRepository.findAll();
        List<AirlineDTO> newList = AirlineConverter.fromEntityList(oldList, e -> AirlineConverter.fromEntity(e));
        return newList;
    }

    @Override
    public void save(AirlineDTO airline) {
        Airline newAirline = AirlineConverter.toEntity(airline);
        airlineRepository.save(newAirline);
    }

    @Override
    public void delete(AirlineDTO airline) {
        Airline newAirline = AirlineConverter.toEntity(airline);
        airlineRepository.deleteById(newAirline.getId());
    }

    @Transactional
    @Override
    public boolean modify(AirlineDTO airline) {
        Airline a = AirlineConverter.toEntity(airline);
        if(a == null) {
            return false;
        }
        airlineRepository.modifyAirline(a.getId(), a.getAddress(), a.getDescription(), a.getLuggagePrice(), a.getName(), a.getOfficeDestination());
        return true;
    }

    @Override
    public AirlineDTO findById(Long id) {
        Airline newAirline = airlineRepository.findById(id).orElse(null);
        AirlineDTO newAirlineDTO = AirlineConverter.fromEntity(newAirline);
        return newAirlineDTO;
    }
}
