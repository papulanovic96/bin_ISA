package com.bin448.backend.service;

import com.bin448.backend.converter.FlightConverter;
import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.Flight;
import com.bin448.backend.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void save(FlightDTO newFlight) {
        Flight nf = FlightConverter.toEntity(newFlight);
        flightRepository.save(nf);
    }

    @Override
    public List<FlightDTO> findAll() {
        List<Flight> list = flightRepository.findAll();
        List<FlightDTO> listDTO = FlightConverter.fromEntityList(list, e -> FlightConverter.fromEntity(e));
        return listDTO;
    }
}
