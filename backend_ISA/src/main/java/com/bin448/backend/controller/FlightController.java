package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newFlight(@RequestBody FlightDTO f) {
        flightService.save(f);
        return new ResponseEntity<>("flight created!", HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public  ResponseEntity<List<FlightDTO>> findAll() {
        List<FlightDTO> flightDTOList = flightService.findAll();
        return new ResponseEntity<>(flightDTOList, HttpStatus.OK);
    }
}
