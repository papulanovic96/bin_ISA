package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.FlightDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {
    void save(FlightDTO newFlight);
    List<FlightDTO> findAll();

}
