package com.bin448.backend.controller;

import com.bin448.backend.converter.FlightConverter;
import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.Flight;
import com.bin448.backend.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/rate/{userId},{flightId},{rate}")
    public boolean rateAirline(@PathVariable Long userId,@PathVariable Long flightId, @PathVariable Double rate){
        return flightService.rateFlight(userId, flightId, rate);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newFlight(@RequestBody FlightDTO f) {
        flightService.save(f);
        return new ResponseEntity<>("flight created!", HttpStatus.OK);
    }

    @RequestMapping(value = "/check/{from}, {to}, {u1}, {u2}", method = RequestMethod.GET)
    public ResponseEntity<List<FlightDTO>> check(@PathVariable String from, @PathVariable String to, @PathVariable String u1, @PathVariable String u2){
        List<Flight> dList = flightService.checkIT(from, to, u1, u2);
        List<FlightDTO> ListaAll = flightService.findAll();
        List<FlightDTO> enList;
        if(from.equals("undefined") && to.equals("undefined") && u1.equals("undefined") && u2.equals("undefined")){
            enList = ListaAll;
        } else {
            enList = FlightConverter.fromEntityList(dList, e-> FlightConverter.fromEntity(e));
        }
        return new ResponseEntity<>(enList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public  ResponseEntity<List<FlightDTO>> findAll() {
        List<FlightDTO> flightDTOList = flightService.findAll();
        return new ResponseEntity<>(flightDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public  ResponseEntity<String> delete(@PathVariable Long id) {
        flightService.delete(flightService.findById(id));
        return new ResponseEntity<>("Deleted flight: " + id, HttpStatus.OK);
    }
}
