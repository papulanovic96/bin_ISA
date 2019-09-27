package com.bin448.backend.service;

import com.bin448.backend.converter.CarReservationConverter;
import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarRate;
import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;
import com.bin448.backend.repository.CarRateRepository;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.CarReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Service
public class CarReservationServiceImpl implements CarReservationService{

    private CarReservationRepository crr;
    private CarRepository carRepository;
    private CarRateRepository carRateRep;
    public CarReservationServiceImpl(CarRateRepository c,CarRepository carRepository, CarReservationRepository crr)

    {
        this.carRateRep = c;
        this.carRepository = carRepository;
        this.crr = crr;
    }

    @Override
    @Transactional(readOnly = true)
    public CarReservation findByUserId(Long id) {
        return crr.findByUserId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CarReservation findBycarId(Long id) {
        return crr.findByCarId(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void addReservation(CarReservationDTO crrr) {
        CarReservation cr = CarReservationConverter.toEntity(crrr);

        crr.save(cr);
    }



    @Override
    @Transactional(readOnly = true)
    public boolean IsDeleteReservationPosible(Long id, Long userId) {
        CarReservation carReservation = crr.checkCanceling(id,userId);
        if(carReservation==null)
            return false;
        else
            return  true;

    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserAbleToRate(Long id, Long userId) {
        List<CarReservation> reservations= crr.findAllByCarIdAndUserId(id,userId);
        List<CarRate> rates = carRateRep.findAllByCar_CarIdAndUser_Id(id,userId);
        if(reservations.size()!=0 && rates.size()==0)
            return true;
        else
            return false;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String removeReservation(Long id, Long userId) {
        String ret= "ERROR";
        try {
            CarReservation carR = crr.findByCarIdAndUserId(id,userId);
            crr.deleteAllByCarIdAndUserId(id,userId);
            carRepository.modifyReserved(false, id);
            ret = "SUCCESS";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return  ret;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean IsUserReservedCar(Long id, Long userId) {
        List<CarReservation> reservations = crr.findAllByCarIdAndUserId(id,userId);

        if (reservations.size()==0)
            return false;
        else
            return true;
    }

}
