package com.bin448.backend.converter;

import com.bin448.backend.entity.CarRate;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component


public class CarRateConverter {

    private static CarRepository cr;
    private static UserRepository ur;
    public CarRateConverter(UserRepository ur, CarRepository cr){
        this.cr = cr;
        this.ur = ur;
    }

    public static CarRate toEntity(CarRateDTO crDTO){
        CarRate carR = new CarRate();
        carR.setId(crDTO.getId());
        carR.setRate(crDTO.getRate());
        carR.setCar(cr.getCarByCarId((crDTO.getCarID())));
        carR.setUser(ur.getUserById(crDTO.getUserID()));
        return carR;
    }
    public static CarRateDTO fromEntity(CarRate carRate){
        CarRateDTO crDTO = new CarRateDTO();
        crDTO.setRate(carRate.getRate());
        crDTO.setId(carRate.getId());
        crDTO.setCarID(carRate.getCar().getCarId());
        crDTO.setUserID(carRate.getUser().getId());
        return crDTO;
    }

}
