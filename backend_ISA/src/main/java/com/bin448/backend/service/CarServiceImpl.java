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
    public String addCar(CarDTO car) {
        String ret = "ERROR";
        try {
            Car c = CarConverter.toEntity(car);
            c.setDeleted(false);
            cr.save(c);
            ret = "SUCCESS";
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            return  ret;
        }
        }

    @Override
    public String removeCar(String reg) {

        String ret = "ERROR";
        try {
            Car c = cr.getCarByRegID(reg);
            if(!c.isReserved()) {
                cr.deleteSelectedCar(true, reg);
                ret = "SUCCESS";
            }
            else
                ret = "CAR IS RESERVED!";
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            return  ret;
        }
    }

    @Override
    public List<CarDTO> search(String model, String type, Integer from, Integer to, Integer nos) {
        if(nos!=-5) {
            List<Car> cars = cr.search(model, type, from, to, nos);
            List<CarDTO> carDTOS = new ArrayList<>();
            for (Car c : cars)
                carDTOS.add(CarConverter.fromEntity(c));
            return carDTOS;

        }
        else{
            List<Car> cars = cr.searchWithoutSeats(model, type, from, to);
            List<CarDTO> carDTOS = new ArrayList<>();
            for (Car c : cars)
                carDTOS.add(CarConverter.fromEntity(c));
            return carDTOS;

        }
        }

    @Override
    public CarDTO getCar(String reg) {
        CarDTO c = CarConverter.fromEntity(cr.getCarByRegID(reg));
        return c;
    }

    @Override
    public List<CarDTO> findAll(String serviceName) {
        List <Car> auti = cr.findAllByCarService_CarServiceName(serviceName);
        List<CarDTO> cardto = new ArrayList<>();

            for (int i = 0; i < auti.size(); i++) {

                    cardto.add(CarConverter.fromEntity(auti.get(i)));


            }
                return cardto;

    }

    @Override
    public String modifyReserved(boolean r, Long id) {
        String ret = "ERROR";
        try {
            cr.modifyReserved(r,id);
            ret = "SUCCESS";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return ret;
        }
    }

    @Override
    public String modifyCar(String model, String type, Integer year, Boolean convert, String regID,Long serviceID,Long carID) {
        String ret = "ERROR!";
        try {
            cr.modifyCar(model,type,year,convert,regID,serviceID,carID);
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
            CarRate crA = CarRateConverter.toEntity(cra);
            crr.save(crA);
            Double avg = crr.getAvgRate(cra.getCarID());
            cr.rateSelectedCar(avg,cra.getCarID());

            ret = "SUCCESS!";
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return ret;
        }



    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> all = cr.findAll();
        List<CarDTO> allDTO = new ArrayList<>();
        for(Car c : all) {

                allDTO.add(CarConverter.fromEntity(c));
        }
    return  allDTO;
    }

    @Override
    public boolean isUserRated(Long id, Long userId) {
        List<CarRate> rates = crr.findAllByCar_CarIdAndUser_Id(id,userId);
        if (rates.size()>0)
            return true;
      return false;
    }

    @Override
    public List<CarDTO> getAllReservedCars(Long userID) {
        List<Car> cars = cr.getReservedCars(userID);
        List<CarDTO> carsDTO = new ArrayList<>();
        for(int i=0;i<cars.size();i++){
            carsDTO.add(CarConverter.fromEntity(cars.get(i)));
        }
        return carsDTO;
    }

}
