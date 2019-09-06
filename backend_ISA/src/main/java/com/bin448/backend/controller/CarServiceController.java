package com.bin448.backend.controller;

import com.bin448.backend.converter.CarServiceConverter;
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

    @PostMapping("/remove/{id}")
    public String removeCarService(@PathVariable Long id){
       return css.logicRemoveCarService(id);

    }

    @GetMapping("/find/{name}")
    public CarServiceDTO findCarService(@PathVariable String name){
        return css.findCarService(name);
    }

    @GetMapping("findById/{id}")
    public CarServiceDTO findById(@PathVariable Long id){
        return CarServiceConverter.fromEntity(csr.getCarServiceByCarServiceId(id));

    }
    @PostMapping("/modifyCarService/{name},{adress},{descrip},{location},{id}")
    public String modifyCarS(@PathVariable String name, @PathVariable String adress, @PathVariable String descrip,@PathVariable String location,@PathVariable Long id ){
        return css.modifyService(name,adress,descrip,location,id);
    }

    @GetMapping("/getAllItems/{name}")
    public List<CarServicePriceListDTO> getAllItems(@PathVariable String name){
        return css.getAllItems(name);
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody CarServicePriceListDTO csDTO){
        CarServicePriceList cs = CarServicePriceListConverter.toEntity(csDTO);
        return ResponseEntity.ok(css.addItem(cs));
    }

    @GetMapping("/search/{address},{name}")
    public List<CarServiceDTO>search(@PathVariable String address,@PathVariable String name){
        return css.search(address,name);
    }

    @GetMapping("/getItem/{id}")
    public CarServicePriceListDTO getItem(@PathVariable Long id){
        return css.getItem(id);
    }

    @PostMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        return ResponseEntity.ok(css.deleteItem(id));
    }

    @PostMapping("/modifyItem/{Ime},{Cena},{Id}")
    public String modifyItem(@PathVariable String Ime,@PathVariable Double Cena, @PathVariable Long Id){
        return css.changeItem(Ime,Cena,Id);
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

    @GetMapping("/tryToRate/{userID},{serviceID}")
    public boolean ttr(@PathVariable Long userID,@PathVariable Long serviceID){
        return css.isUserAbleToRateService(userID,serviceID);
    }
}
