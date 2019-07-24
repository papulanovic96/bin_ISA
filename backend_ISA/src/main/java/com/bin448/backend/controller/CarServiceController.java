package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.service.CarServiceService;
import com.bin448.backend.service.CarServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carService")
public class CarServiceController {

    private CarServiceService css;
    public CarServiceController(CarServiceService css){
        this.css = css;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addCarService(@RequestBody CarServiceDTO carService){
        css.addCarService(carService);
        return ResponseEntity.ok("Success");
    }

}
