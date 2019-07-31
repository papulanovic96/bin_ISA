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
        cr.setCarREG(crDTO.getRegID());
        cr.setEndDate(crDTO.getEndDate());
        cr.setUsername(crDTO.getUsername());
        cr.setStartDate(crDTO.getStartDate());
        return  cr;
    }
    public static CarReservationDTO fromEntity(CarReservation cr){
        CarReservationDTO crDTO = new CarReservationDTO();
        crDTO.setEndDate(cr.getEndDate());
        crDTO.setStartDate(cr.getStartDate());
        crDTO.setRegID(cr.getCarREG());
        crDTO.setUsername(cr.getUsername());
        return crDTO;
    }
}
