package com.bin448.backend.controller;


import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarRate;
import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;


import com.bin448.backend.service.CarReservationService;
import com.bin448.backend.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/Car")
public class CarController {

    private CarService css;
    private CarReservationService crs;
    public CarController(CarReservationService crs,CarService css){
        this.css = css;
        this.crs = crs;

    }

    @PostMapping("/add")
    public String addCar(@RequestBody CarDTO car){

       return css.addCar(car);
    }
    @PostMapping("/remove/{regID}")
    public String removeCar(@PathVariable String regID){
        return css.removeCar(regID);
    }

/*    @PostMapping("/rate/{regID},{rate}")
    public ResponseEntity<String> rateCar(@PathVariable String regID, @PathVariable Double rate){
        Car c = css.getCar(regID);
        c.getRates().add(rate);
        css.
    }
*/
//ovu mogu svi
    @GetMapping("/find/{regID}")
    public CarDTO findCarbyregID(@PathVariable String regID){
        return css.getCar(regID);
    }
//ovu mogu svi
    @GetMapping("/findAll/{serviceName}")
    public List<CarDTO> getAllCars(@PathVariable String serviceName){
        return css.findAll(serviceName);
    }

    @PostMapping("/reserve")
    public String reserveCar(@RequestBody CarReservationDTO cr){
     crs.addReservation(cr);
     return css.modifyReserved(true,cr.getRegID());

    }

    @PostMapping("/unreserve")
    public String unreserveCar(@RequestBody CarReservationDTO cr){
        String ret = "ERROR";
        try {
            crs.deleteReservation(cr.getRegID(), cr.getUsername());
            ret = "SUCCESS";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return ret;
        }
        }

    @PostMapping("/modifyCar/{model},{type},{year},{conv},{regID}")
    public ResponseEntity<String> modifyCars(@PathVariable String model,@PathVariable String type,@PathVariable Integer year,@PathVariable Boolean conv,@PathVariable String regID){
    return ResponseEntity.ok(css.modifyCar(model,type,year,conv,regID));
    }

    @PostMapping("/rateCar")
    public String rateCar(@RequestBody CarRateDTO cr){
        return css.rateCar(cr);
    }

    @GetMapping("/getAvgGrade/{regID}")
    public Double getAvgRate(@PathVariable String regID){
        return css.getAvgGrade(regID);
    }
    @GetMapping("/getAllCars")
    public List<CarDTO> getAll(){
        return css.getAllCars();
    }
}
