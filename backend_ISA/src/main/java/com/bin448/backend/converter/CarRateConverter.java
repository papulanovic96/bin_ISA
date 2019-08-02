package com.bin448.backend.converter;

import com.bin448.backend.entity.CarRate;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.repository.CarRepository;
import org.springframework.stereotype.Component;

@Component


public class CarRateConverter {

    private static CarRepository cr;

    public CarRateConverter(CarRepository cr){
        this.cr = cr;
    }

    public static CarRate toEntity(CarRateDTO crDTO){
        CarRate carR = new CarRate();
        carR.setId(crDTO.getId());
        carR.setRate(crDTO.getRate());
        carR.setCar(cr.getCarByRegID(crDTO.getCarID()));
        return carR;
    }
    public static CarRateDTO fromEntity(CarRate carRate){
        CarRateDTO crDTO = new CarRateDTO();
        crDTO.setRate(carRate.getRate());
        crDTO.setId(carRate.getId());
        crDTO.setCarID(carRate.getCar().getRegID());
        return crDTO;
    }

}
