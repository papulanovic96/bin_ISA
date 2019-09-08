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
    public CarReservation findByUserId(Long id) {
        return crr.findByUserId(id);
    }

    @Override
    public CarReservation findBycarId(Long id) {
        return crr.findByCarId(id);
    }

    @Override
    public void addReservation(CarReservationDTO crrr) {
        CarReservation cr = CarReservationConverter.toEntity(crrr);
        crr.save(cr);
    }




    @Override
    public boolean IsDeleteReservationPosible(Long id, Long userId) {
        CarReservation carReservation = crr.checkCanceling(id,userId);
        if(carReservation==null)
            return false;
            else
                return  true;
   /*    List<CarReservation> carRes = crr.findAllByCarIdAndUserId(id, userId);
        Car c = carRepository.getCarByCarId(id);
        if (carRes.size() !=0  && c!=null && c.isReserved()) {
            for (CarReservation carR : carRes) {
                Date start = carR.getStartDate();
                Date date = new Date(System.currentTimeMillis());
                if (start.getTime() > date.getTime()) {
                    long secs = (start.getTime() - date.getTime()) / 1000;
                    long hours = secs / 3600;
                    if (hours >= 24) {

                        return true;
                    } else {
                        return false;
                    }
                } else
                    return false;
            }
            return false;
        }
        return false;*/
    }

    @Override
    public boolean isUserAbleToRate(Long id, Long userId) {
        List<CarReservation> reservations= crr.findAllByCarIdAndUserId(id,userId);
        List<CarRate> rates = carRateRep.findAllByCar_CarIdAndUser_Id(id,userId);
        if(reservations.size()!=0 && rates.size()==0)
            return true;
        else
            return false;
        /*
        Date date = new Date();
        boolean finded = false;

        if(reservations!=null) {
            if(reservations.size()==0)
                return false;
            else {
                for (CarReservation reservation : reservations) {
                    if (date.compareTo(reservation.getEndDate()) > 0) {
                        finded = true;
                        break;
                    }
                }
            }


            if (finded == true) {
                if (rates != null || rates.size()>0) {
                    for (CarRate rate : rates) {
                        if (rate.getRate() != null) {
                            return false;
                        }
                    }
                    return true;
                } else return true;
            }
            else return false;

        }

        else return false;*/
    }

    @Override
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
    public boolean IsUserReservedCar(Long id, Long userId) {
        List<CarReservation> reservations = crr.findAllByCarIdAndUserId(id,userId);
     /*   Date date = new Date();

        if(reservations.size()>0){
            for(CarReservation reservation:reservations){
                if(date.compareTo(reservation.getEndDate()) < 0){
                    return true;
                }
            }
        }
        return false;
    */
    if (reservations.size()==0)
        return false;
    else
        return true;
    }

}
