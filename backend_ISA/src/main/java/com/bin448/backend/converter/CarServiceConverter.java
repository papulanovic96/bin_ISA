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
    public static CarServiceDTO fromEntity(CarService cs){
        CarServiceDTO csDTO = new CarServiceDTO();
        csDTO.setCarServiceAddress(cs.getCarServiceAddress());
        csDTO.setId(cs.getCarService_id());
        csDTO.setCarServiceDescription(cs.getCarServiceDescription());
        csDTO.setCarServiceName(cs.getCarServiceName());
        return csDTO;


    }
    public static CarService toEntity(CarServiceDTO csDTO){
        CarService cs = new CarService();
        cs.setCarService_id(csDTO.getId());
        cs.setAvgGrade(csDTO.getAvgGrade());
        cs.setCarServiceAddress(csDTO.getCarServiceAddress());
        cs.setCarServiceDescription(csDTO.getCarServiceDescription());
        cs.setCarServiceLocation(csDTO.getCarServiceLocation());
        cs.setCarServiceName(csDTO.getCarServiceName());
        return cs;
    }
}
