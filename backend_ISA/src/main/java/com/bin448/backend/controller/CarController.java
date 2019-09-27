package com.bin448.backend.controller;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarRate;
import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.*;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;

import com.bin448.backend.service.CarReservationService;
import com.bin448.backend.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/getDiscount")
    public ResponseEntity<List<CarDiscountDTO>> getOnDiscount(){
        return ResponseEntity.ok(css.getAllCarDiscounts());
    }

    @GetMapping("/types")
    public List<CarTypeDTO>getTypes(){
        return css.getTypes();
    }

    @PostMapping("/setDiscount")
    public boolean setDiscount(@RequestBody CarDiscountDTO carDiscountDTO){
        return css.setDiscount(carDiscountDTO);
    }

    @GetMapping("/getAvailable/{ty},{f},{t},{start},{end}")
    public List<CarDTO> getAvailable(@PathVariable String ty, @PathVariable Integer f, @PathVariable Integer t, @PathVariable String start, @PathVariable String end){
        return css.getAvailableCars(ty, f, t, start, end);
    }

    @GetMapping("/tryToRate/{regID},{userId}")
    public boolean tryToRate(@PathVariable Long regID,@PathVariable Long userId){
        return crs.isUserAbleToRate(regID,userId);
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
    public void reserveCar(@RequestBody CarReservationDTO cr){
        crs.addReservation(cr);

    }

    @GetMapping("/tryToUnreserve/{carId},{userId}")
    public boolean CanUserUnreserveCar(@PathVariable Long carId,@PathVariable Long userId){
        return crs.IsDeleteReservationPosible(carId,userId);
    }

    @PostMapping("/deleteRes/{carId},{userId}")
    public String removeReservation(@PathVariable Long carId,@PathVariable Long userId){

        return crs.removeReservation(carId,userId);

    }

    @PostMapping("/modifyCar/{model},{type},{year},{conv},{regID},{sID},{cID}")
    public ResponseEntity<String> modifyCars(@PathVariable String model,@PathVariable String type,@PathVariable Integer year,@PathVariable Boolean conv,@PathVariable String regID,@PathVariable Long sID,@PathVariable Long cID){
        return ResponseEntity.ok(css.modifyCar(model,type,year,conv,regID,sID,cID));
    }

    @PostMapping("/rateCar")
    public String rateCar(@RequestBody CarRateDTO cr){

        return css.rateCar(cr);
    }

    @GetMapping("/search/{model},{type},{from},{to},{nos}")
    public List<CarDTO> search(@PathVariable String model,@PathVariable String type,@PathVariable Integer from,@PathVariable Integer to, @PathVariable Integer nos){
        return css.search(model,type,from,to,nos);
    }

    /*  @GetMapping("/getAvgGrade/{regID}")
      public Double getAvgRate(@PathVariable String regID){
          return css.getAvgGrade(regID);
      }*/
    @GetMapping("/getAllCars")
    public List<CarDTO> getAll(){
        return css.getAllCars();
    }
    @GetMapping("/IsUserReservedCar/{id},{id2}")
    public boolean IURC(@PathVariable Long id,@PathVariable Long id2){
        return crs.IsUserReservedCar(id,id2);
    }
    @GetMapping("/IsUserRated/{id},{id2}")
    public boolean IUR(@PathVariable Long id,@PathVariable Long id2){
        return css.isUserRated(id,id2);
    }
    @GetMapping("/getReservedCars/{idUser}")
    public List<CarDTO> getReserved(@PathVariable Long idUser){
        return css.getAllReservedCars(idUser);
    }
}
