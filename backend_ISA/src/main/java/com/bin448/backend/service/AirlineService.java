package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.AirlineDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlineService {
    List<AirlineDTO> findAll();
}
