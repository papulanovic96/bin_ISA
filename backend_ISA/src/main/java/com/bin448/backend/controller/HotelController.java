package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.AddressDTO;
import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotel(hotelId));
    }

    @PostMapping
    public ResponseEntity<String> addHotel(@RequestBody @Valid HotelDTO hotelDTO) {
        hotelService.addHotel(hotelDTO);
        return ResponseEntity.ok(String.format("Hotel %s is added", hotelDTO.getName()));
    }

    @GetMapping("/getMenu/{hotelId}")
    public ResponseEntity<HashMap<String, Double>> getMenu(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getMenu(hotelId));
    }

    @GetMapping("/getDescription/{hotelId}")
    public ResponseEntity<String> getDescription(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getDescription(hotelId));
    }

    @GetMapping("/addMenuItem/{name}/{price}/{hotelId}")
    public ResponseEntity<String> addMenuItem(@PathVariable String name, @PathVariable Double price, @PathVariable Long hotelId) {
        hotelService.addMenuItem(name, price, hotelId);
        return ResponseEntity.ok("Menu item is successfully added");
    }

    @GetMapping("/removeMenuItem/{name}/{hotelId}")
    public ResponseEntity<String> removeMenuItem(@PathVariable String name, @PathVariable Long hotelId) {
        hotelService.removeMenuItem(name, hotelId);
        return ResponseEntity.ok("Menu item is successfully removed");
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> findAll() {
        return ResponseEntity.ok(hotelService.findAll());
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> findAllAddresses() {
        return ResponseEntity.ok(hotelService.findAllAddresses());
    }

    @PutMapping("/remove/{id}")
    public ResponseEntity<Boolean> removeRoom(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.removeHotel(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> changeRoom(@PathVariable Long id, @RequestBody @Valid HotelDTO hotelDTO) {
        hotelService.changeHotel(hotelDTO, id);
        return ResponseEntity.ok(String.format("Hotel with id %s has been successfully changed", id));
    }

    @PutMapping("/search/{name}/{address}/{arrival}/{end}")
    public ResponseEntity<List<HotelDTO>> searchHotels(@PathVariable String name, @PathVariable String address, @PathVariable String arrival, @PathVariable String end) {
        return ResponseEntity.ok(hotelService.searchHotels(name, address, arrival, end));
    }

    @GetMapping("/middleGrade/{hotelId}")
    public ResponseEntity<Double> getMiddleGrade(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getMiddleGrade(hotelId));
    }
}
