package com.bin448.backend.controller;

import com.bin448.backend.converter.CarServiceConverter;
import com.bin448.backend.converter.CarServicePriceListConverter;
import com.bin448.backend.entity.CarServicePriceList;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.entity.DTOentity.RentACarBranchOfficeDTO;
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

    @PostMapping("/office/add")
    public boolean addOffice(@RequestBody RentACarBranchOfficeDTO dto){
        return css.addBranchOffice(dto);
    }

    @GetMapping("/office/delete/{id}")
    public boolean deleteOffice(@PathVariable Long id){
        return css.deleteBranchOffice(id);
    }

    @GetMapping("/office/getAll/{id}")
    public List<RentACarBranchOfficeDTO> getAllOffices(@PathVariable Long id){
        return css.getAllBranches(id);
    }

    @GetMapping("/office/getOne/{id}")
    public RentACarBranchOfficeDTO getOne(@PathVariable Long id){
        return css.getByID(id);
    }

    @GetMapping("/office/modify/{address},{id}")
    public String officeMod(@PathVariable String address, @PathVariable Long id){
        return css.updateBranchOffice(address, id);
    }

    @PostMapping("/add")
    public String addCarService(@RequestBody CarServiceDTO carService){
        return css.addCarService(carService);

    }


    @GetMapping("/totalPrice/{array}")
    public Integer getTotal(@PathVariable Integer[] array){
        Integer sum = css.getFullPrice(array);

        return sum;
    }


    @GetMapping("/getPrice/{id}")
    public Integer getTotal(@PathVariable Long id){
        return css.getPrice(id);
    }


    @GetMapping("/updateProfit/{profit},{id}")
    public void updateProfit(@PathVariable Integer profit,@PathVariable Long id){
        css.updateProfit(profit,id);
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

    @GetMapping("/getAllItems/{idService}")
    public List<CarServicePriceListDTO> getAllItems(@PathVariable Long idService){
        return css.getAllItems(idService);
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

    @PostMapping("/modifyItem/{Cena},{Id}")
    public String modifyItem(@PathVariable Double Cena, @PathVariable Long Id){
        return css.changeItem(Cena,Id);
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
