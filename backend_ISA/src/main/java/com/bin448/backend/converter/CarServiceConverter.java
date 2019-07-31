package com.bin448.backend.converter;

import com.bin448.backend.controller.CarServiceController;
import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarService;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CarServiceConverter {
    private static CarRepository carR;
    private static CarServiceRepository crr;
    public CarServiceConverter(CarRepository cr, CarServiceRepository crr) {
        this.crr = crr;
        carR = cr;
    }
    public static CarServiceDTO fromEntity(/*@org.jetbrains.annotations.NotNull*/ CarService cs){
        CarServiceDTO csDTO = new CarServiceDTO();
        List<String> listaReg = new ArrayList<>();
        csDTO.setCarServiceAddress(cs.getCarServiceAddress());
        csDTO.setCarServiceDescription(cs.getCarServiceDescription());
        csDTO.setCarServiceMenu(cs.getCarServiceMenu());
        csDTO.setCarServiceName(cs.getCarServiceName());
        List<Car> vozila = carR.findAllByCarService_CarServiceName(cs.getCarServiceName());
        for (Car c : vozila){
            listaReg.add(c.getRegID());
        }
        csDTO.setCarsCollection(listaReg);
        return csDTO;


    }
    public static CarService toEntity(/*@org.jetbrains.annotations.NotNull*/ CarServiceDTO csDTO){
        CarService cs = new CarService();
        cs.setAvgGrade(csDTO.getAvgGrade());
        cs.setCarServiceAddress(csDTO.getCarServiceAddress());
        cs.setCarServiceDescription(csDTO.getCarServiceDescription());
        cs.setCarServiceMenu(csDTO.getCarServiceMenu());
        cs.setCarServiceLocation(csDTO.getCarServiceLocation());
        cs.setCarServiceName(csDTO.getCarServiceName());
        return cs;
    }
}
