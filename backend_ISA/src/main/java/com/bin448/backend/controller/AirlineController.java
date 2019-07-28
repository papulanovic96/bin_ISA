package com.bin448.backend.controller;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.service.AirlineService;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<AirlineDTO>> findAll() {
        List<AirlineDTO> newList = airlineService.findAll();
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

}
