package com.bin448.backend.controller;


import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;


import com.bin448.backend.service.CarReservationService;
import com.bin448.backend.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Car")
public class CarController {

    private CarService css;
    private CarReservationService crs;
    public CarController(CarReservationService crs,CarService css){
        this.css = css;
        this.crs = crs;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addCar(@RequestBody CarDTO car){
        css.addCar(car);
        return ResponseEntity.ok("Success");
    }
    @PostMapping("/remove/{regID}")
    public ResponseEntity<String> removeCar(@PathVariable String regID){
        css.removeCar(regID);
        return ResponseEntity.ok("Succcess");
    }
//ovu mogu svi
    @GetMapping("/find/{regID}")
    public ResponseEntity<CarDTO> findCarbyregID(@PathVariable String regID){
        return ResponseEntity.ok(css.getCar(regID));
    }
//ovu mogu svi
    @GetMapping("/findAll/{serviceName}")
    public ResponseEntity<List<CarDTO>> getAllCars(@PathVariable String serviceName){
        return ResponseEntity.ok(css.findAll(serviceName));
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveCar(@RequestBody CarReservationDTO cr){
     crs.addReservation(cr);
     css.modifyReserved(true,cr.getRegID());
     return  ResponseEntity.ok("Success");
    }

    @PostMapping("/unreserve")
    public ResponseEntity<String> unreserveCar(@RequestBody CarReservationDTO cr){
        return  ResponseEntity.ok(crs.deleteReservation(cr.getRegID(),cr.getUsername()));
    }

}
