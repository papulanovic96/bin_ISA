package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.service.CarServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carService")
public class CarServiceController {

    private CarServiceService css;

    public CarServiceController(CarServiceService css) {
        this.css = css;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addCarService(@RequestBody CarServiceDTO carService) {
        css.addCarService(carService);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/remove/{name}")
    public ResponseEntity<String> removeCarService(@PathVariable String name) {

        css.removeCarService(name);
        return ResponseEntity.ok("Succcess");
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Object> findCarService(@PathVariable String name) {
        return ResponseEntity.ok(css.findCarService(name));
    }

}
