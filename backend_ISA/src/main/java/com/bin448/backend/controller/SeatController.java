package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @RequestMapping( value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlaneSeatDTO>> findAll() {
        List<PlaneSeatDTO> allSeats = seatService.findAll();
        return new ResponseEntity<List<PlaneSeatDTO>>(allSeats, HttpStatus.OK);
    }

    @RequestMapping( value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<PlaneSeatDTO> findById(@PathVariable Long id) {
        PlaneSeatDTO ps = seatService.findById(id);
        return new ResponseEntity<PlaneSeatDTO>(ps, HttpStatus.OK);
    }

    @RequestMapping( value = "/addSeat", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addSeat(@RequestBody PlaneSeatDTO planeSeat) {
        seatService.save(planeSeat);
        return new ResponseEntity<String>("Seat with ID: " + planeSeat.getId() + " added!", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteSeat/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSeat(@PathVariable Long id) {
        PlaneSeatDTO newSeat = seatService.findById(id);
        if(newSeat.isReserved()) {
            return  new ResponseEntity<String>("Seat may be reserved.", HttpStatus.METHOD_NOT_ALLOWED);
        } else {
            seatService.delete(newSeat);
            return new ResponseEntity<String>("Seat with ID: " + id +" deleted!", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/modifySeat/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modifySeat(@PathVariable Long id, @RequestBody PlaneSeatDTO seat) {
        seat.setId(id);
        boolean modifyYes = seatService.modifySeat(seat);
        if(modifyYes) {
            return new ResponseEntity<String>("Seat with ID: " + id + " modified!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Seat not found or it's reserved!", HttpStatus.NOT_FOUND);
        }
    }
}
