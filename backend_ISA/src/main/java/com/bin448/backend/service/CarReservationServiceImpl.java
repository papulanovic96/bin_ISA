package com.bin448.backend.service;

import com.bin448.backend.converter.CarReservationConverter;
import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.CarReservationRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class CarReservationServiceImpl implements CarReservationService{

    private CarReservationRepository crr;
    private CarRepository carRepository;
    public CarReservationServiceImpl(CarRepository carRepository, CarReservationRepository crr)

    {
        this.carRepository = carRepository;
        this.crr = crr;
    }

    @Override
    public CarReservation findByUsername(String username) {
        return crr.findByUsername(username);
    }

    @Override
    public CarReservation findByRegId(String regID) {
        return crr.findByCarREG(regID);
    }

    @Override
    public void addReservation(CarReservationDTO crrr) {
        CarReservation cr = CarReservationConverter.toEntity(crrr);
        crr.save(cr);
    }

    @Override
    public String deleteReservation(String regID, String username) {
        String ret = "NOT ALLOWED";
        CarReservation carR = crr.findByCarREGAndUsername(regID,username);
        Date start = carR.getStartDate();
        Date date = new Date(System.currentTimeMillis());
        if(start.getTime() > date.getTime()) {
            long secs = (start.getTime() - date.getTime()) / 1000;
            long hours = secs / 3600;
            if(hours>=24){
                crr.deleteByCarResId(carR.getCarResId());
                carRepository.modifyReserved(false,regID);
                ret = "SUCCESS";
                        return ret;
            }
            else{
                return ret;
            }
        }
        else
            return  ret;
    }
}
