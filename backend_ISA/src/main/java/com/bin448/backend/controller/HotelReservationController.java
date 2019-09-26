package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.service.HotelReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class HotelReservationController {

    private final HotelReservationService hotelReservationService;

    public HotelReservationController(HotelReservationService hotelReservationService) {
        this.hotelReservationService = hotelReservationService;
    }

    @GetMapping("/check/{id},{username},{grade}")
    public boolean check(@PathVariable Long id,@PathVariable String username, @PathVariable Double grade){
        return hotelReservationService.rateHotel(id,username,grade);
    }


    @PostMapping
    public ResponseEntity<String> addReservation(@RequestBody HotelReservationDTO reservation) {
        hotelReservationService.addReservation(reservation);
        return ResponseEntity.ok("Successfully added reservation");
    }

    @PostMapping("/discount")
    public ResponseEntity<String> addFastReservation(@RequestBody FastHotelReservationDTO reservation) {
        hotelReservationService.addFastHotelReservation(reservation);
        return ResponseEntity.ok("Successfully added fast reservation");
    }

}
