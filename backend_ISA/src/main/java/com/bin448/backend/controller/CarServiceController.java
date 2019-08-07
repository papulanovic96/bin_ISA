package com.bin448.backend.controller;

import com.bin448.backend.converter.CarServicePriceListConverter;
import com.bin448.backend.entity.CarServicePriceList;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.repository.CarServiceRepository;
import com.bin448.backend.service.CarServiceService;
import com.bin448.backend.service.CarServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carService")
public class CarServiceController {

    private CarServiceService css;
    private CarServiceRepository csr;
    public CarServiceController(CarServiceService css, CarServiceRepository csr){
        this.css = css;
        this.csr = csr;

    }

    @PostMapping("/add")
    public String addCarService(@RequestBody CarServiceDTO carService){
        return css.addCarService(carService);

    }

    @PostMapping("/remove/{name}")
    public String removeCarService(@PathVariable String name){
       return css.logicRemoveCarService(name);

    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Object> findCarService(@PathVariable String name){
        return ResponseEntity.ok(css.findCarService(name));
    }

    @GetMapping("/getAllItems/{name}")
    public ResponseEntity<List<CarServicePriceListDTO>> getAllItems(@PathVariable String name){
        return ResponseEntity.ok(css.getAllItems(name));
    }

    @PostMapping("/addItem")

    public ResponseEntity<String> addItem(@RequestBody CarServicePriceListDTO csDTO){
        CarServicePriceList cs = CarServicePriceListConverter.toEntity(csDTO);
        return ResponseEntity.ok(css.addItem(cs));
    }

    @PostMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return ResponseEntity.ok(css.deleteItem(id));
    }

    @PostMapping("/modifyItem/{Ime},{Cena},{Id}")
    public ResponseEntity<String> modifyItem(@PathVariable String Ime,@PathVariable Double Cena, @PathVariable Long Id){
        return ResponseEntity.ok(css.changeItem(Ime,Cena,Id));
    }

    @PostMapping("/rateCarService")
    public ResponseEntity<String> rateCarService(@RequestBody CarServiceRateDTO csr){
        return ResponseEntity.ok(css.carServiceRate(csr));
    }

    @GetMapping("/getAvgGrade/{name}")
    public ResponseEntity<Double> getAvgRate(@PathVariable String name){
        return ResponseEntity.ok(css.getAvgGrade(name));
    }
    @GetMapping("/getAllCarServices")
    public List<CarServiceDTO>getCarServices(){
        return css.getAll();
    }
}
