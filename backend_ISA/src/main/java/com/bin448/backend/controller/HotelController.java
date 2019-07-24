package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<String> addHotel(@RequestBody @Valid HotelDTO hotelDTO) {
        hotelService.addHotel(hotelDTO);
        return ResponseEntity.ok(String.format("Hotel %s is added", hotelDTO.getName()));
    }
}
