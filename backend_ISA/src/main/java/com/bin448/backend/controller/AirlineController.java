package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping("/rate/{userId},{airlineId},{rate}")
    public boolean rateAirline(@PathVariable Long userId,@PathVariable Long airlineId, @PathVariable Double rate){
        return airlineService.rateAirline(userId, airlineId, rate);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<AirlineDTO>> findAll() {
        List<AirlineDTO> newList = airlineService.findAll();
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody AirlineDTO a){
        airlineService.save(a);
        return new ResponseEntity<>("Created!", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        AirlineDTO a = airlineService.findById(id);
        airlineService.delete(a);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ResponseEntity<AirlineDTO> findById(@PathVariable Long id){
        AirlineDTO a = airlineService.findById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyIT2(@PathVariable Long id, @RequestBody AirlineDTO a){
        a.setId(id);
        boolean success = airlineService.modify(a);
        if(success){
            return new ResponseEntity<>("Modified", HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Not found!", HttpStatus.NOT_FOUND);
        }
    }
}
