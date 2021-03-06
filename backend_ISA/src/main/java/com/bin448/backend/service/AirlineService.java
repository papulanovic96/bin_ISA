package com.bin448.backend.service;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AirlineService {
    List<AirlineDTO> findAll();
    String save(AirlineDTO airline);
    String delete(AirlineDTO airline);
    boolean modify(AirlineDTO airline);
    AirlineDTO findById(Long id);
    Boolean rateAirline(Long userId, Long airlineId, Double rate);

}
