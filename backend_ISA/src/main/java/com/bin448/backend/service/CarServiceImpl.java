package com.bin448.backend.service;

import com.bin448.backend.converter.CarConverter;
import com.bin448.backend.converter.CarRateConverter;
import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarRate;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.repository.CarRateRepository;
import com.bin448.backend.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Service

public class CarServiceImpl implements CarService {
    private CarRepository cr;
    private CarRateRepository crr;
    public CarServiceImpl(CarRateRepository crr, CarRepository cr)
    {
        this.crr=crr;
        this.cr=cr;
    }

    @Override
    public void addCar(CarDTO car) {
        Car c = CarConverter.toEntity(car);
        cr.save(c);
    }

    @Override
    public void removeCar(String reg) {
        cr.deleteCarByRegID(reg);
    }

    @Override
    public CarDTO getCar(String reg) {
    CarDTO c = CarConverter.fromEntity(cr.getCarByRegID(reg));
    return c;
    }

    @Override
    public List<CarDTO> findAll(String serviceName) {
        List <Car> auti = cr.findAllByCarService_CarServiceName(serviceName);
        if(auti!=null) {
            List<CarDTO> cardto = new ArrayList<>();
            for (int i = 0; i < auti.size(); i++)
                cardto.add(CarConverter.fromEntity(auti.get(i)));
            return cardto;
        }
        else return null;
    }

    @Override
    public void modifyReserved(boolean r, String reg) {
        cr.modifyReserved(r,reg);
    }

    @Override
    public String modifyCar(String model, String type, Integer year, Boolean convert, String regID) {
        String ret = "ERROR!";
        try {
            cr.modifyCar(model,type,year,convert,regID);
            ret = "SUCCESS";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return  ret;
        }
    }

    @Override
    public String rateCar(CarRateDTO cra) {
        String ret = "ERROR!";
        try {
            CarRate cr = CarRateConverter.toEntity(cra);
            crr.save(cr);
            ret = "SUCCESS!";
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return ret;
        }



    }

    @Override
    public Double getAvgGrade(String regID) {
        Double count = 0D;
        List<CarRate> allRates= crr.findAllByCar_RegID(regID);
        for(CarRate cr : allRates){
            count+=cr.getRate();
        }
        return count/allRates.size();
    }
}
