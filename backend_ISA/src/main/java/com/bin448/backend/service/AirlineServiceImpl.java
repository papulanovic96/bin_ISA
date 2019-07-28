package com.bin448.backend.service;

import com.bin448.backend.converter.AirlineConverter;
import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
