package com.bin448.backend.controller;


import com.bin448.backend.converter.PlaneTicketConverter;
import com.bin448.backend.entity.DTOentity.PlaneTicketDTO;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.service.PlaneTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class PlaneTicketController {

    @Autowired
    private PlaneTicketService planeTicketService;

    @RequestMapping(value = "/findAll" , method = RequestMethod.GET)
    public ResponseEntity<List<PlaneTicketDTO>> findItAll() {
        List<PlaneTicketDTO> listDTO = planeTicketService.findAll();
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody PlaneTicket planeTicket) {
        PlaneTicketDTO planeTicketDTO = PlaneTicketConverter.fromEntity(planeTicket);
        planeTicketService.save(planeTicketDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/reserve/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> reserveT(@PathVariable Long id, @RequestBody PlaneTicketDTO t) {
        t.setId(id);
        boolean modifyYes = planeTicketService.reserve(t);
        if (modifyYes) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
