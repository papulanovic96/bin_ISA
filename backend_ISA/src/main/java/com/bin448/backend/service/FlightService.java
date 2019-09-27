package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.Flight;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

@Service
public interface FlightService {
    String save(FlightDTO newFlight);
    String delete(FlightDTO flightDTO);
    FlightDTO findById(Long id);
    List<Flight> checkIT(String from, String to, String u1, String u2);
    List<FlightDTO> findAll();
    Boolean rateFlight(Long userId, Long flightId, Double rate);

}
