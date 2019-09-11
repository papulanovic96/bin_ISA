package com.bin448.backend.controller;


import com.bin448.backend.entity.DTOentity.DiscountDTO;
import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.DTOentity.NewRoomPriceDTO;
import com.bin448.backend.service.NewRoomPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/newRoomPrice")
public class NewRoomPriceController {

    private final NewRoomPriceService newRoomPriceService;

    public NewRoomPriceController(NewRoomPriceService newRoomPriceService) {
        this.newRoomPriceService = newRoomPriceService;
    }

    @PostMapping("/checkExistence")
    public ResponseEntity<Boolean> checkExistence(@RequestBody NewRoomPriceDTO newRoomPriceDTO) {
        return ResponseEntity.ok(newRoomPriceService.checkIfPricesAlreadyExist(newRoomPriceDTO));
    }

    @PostMapping("/discount")
    public ResponseEntity<String> addDiscount(@RequestBody DiscountDTO discountDTO) {
        newRoomPriceService.addDiscount(discountDTO);
        return ResponseEntity.ok("New discount is added");
    }

    @PostMapping
    public ResponseEntity<String> addNewPrice(@RequestBody NewRoomPriceDTO newRoomPriceDTO) {
        newRoomPriceService.addNewPrice(newRoomPriceDTO);
        return ResponseEntity.ok("New price is added");
    }

    @PutMapping("/validDiscounts")
    public ResponseEntity<List<DiscountDTO>> getValidDiscounts(@RequestBody FastHotelReservationDTO fastHotelReservationDTO) {
        return ResponseEntity.ok(newRoomPriceService.getValidDiscounts(fastHotelReservationDTO));
    }
}