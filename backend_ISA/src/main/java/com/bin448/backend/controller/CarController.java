package com.bin448.backend.controller;


import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;


import com.bin448.backend.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Car")
public class CarController {

    private CarService css;
    public CarController(CarService css){
        this.css = css;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addCarService(@RequestBody CarDTO car){
        css.addCar(car);
        return ResponseEntity.ok("Success");
    }
    @PostMapping("/remove/{regID}")
    public ResponseEntity<String> removeCar(@PathVariable String regID){

        css.removeCar(regID);
        return ResponseEntity.ok("Succcess");
    }

    @GetMapping("/find/{regID}")
    public ResponseEntity<CarDTO> findCarService(@PathVariable String regID){
        return ResponseEntity.ok(css.getCar(regID));
    }

    @GetMapping("/findAll/{id}")
    public ResponseEntity<List<CarDTO>> findCarService(@PathVariable Long id){
        return ResponseEntity.ok(css.findAll(id));
    }

}
