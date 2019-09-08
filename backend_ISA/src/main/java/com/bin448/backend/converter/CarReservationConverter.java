package com.bin448.backend.converter;

import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.CarReservationRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CarReservationConverter extends AbstractConverter{

    private static UserRepository ur;
    private static CarRepository crr;

    public CarReservationConverter(UserRepository ur, CarRepository crr){
        this.crr = crr;
        this.ur = ur;
    }
    public static CarReservation toEntity(CarReservationDTO crDTO){
        CarReservation cr = new CarReservation();
        cr.setCarId(crDTO.getCarId());
        cr.setCarResId(crDTO.getId());
        cr.setEndDate(crDTO.getEndDate());
        cr.setUserId(crDTO.getUserId());
        cr.setStartDate(crDTO.getStartDate());
        return  cr;
    }
    public static CarReservationDTO fromEntity(CarReservation cr){
        CarReservationDTO crDTO = new CarReservationDTO();
        crDTO.setEndDate(cr.getEndDate());
        crDTO.setStartDate(cr.getStartDate());
        crDTO.setCarId(cr.getCarId());
        crDTO.setId(cr.getCarResId());
        crDTO.setUserId(cr.getUserId());
        return crDTO;
    }
}
