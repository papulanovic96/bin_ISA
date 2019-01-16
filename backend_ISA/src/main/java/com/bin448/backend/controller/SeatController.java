package com.bin448.backend.controller;

import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.management.ManagementFactory;
import java.util.List;


@Controller
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @RequestMapping( value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlaneSeat>> findAll() {
        List<PlaneSeat> allSeats = seatService.findAll();
        return new ResponseEntity<List<PlaneSeat>>(allSeats, HttpStatus.OK);
    }

    @RequestMapping( value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<PlaneSeat> findById(@PathVariable Long id) {
        PlaneSeat ps = seatService.findById(id);
        return new ResponseEntity<PlaneSeat>(ps, HttpStatus.OK);
    }
}
