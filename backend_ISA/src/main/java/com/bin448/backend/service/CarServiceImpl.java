package com.bin448.backend.service;

import com.bin448.backend.converter.CarConverter;
import com.bin448.backend.converter.CarDiscountConverter;
import com.bin448.backend.converter.CarRateConverter;
import com.bin448.backend.converter.CarTypeConverter;
import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarDiscountDTO;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.entity.DTOentity.CarTypeDTO;
import com.bin448.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;

import java.util.List;

@Service

public class CarServiceImpl implements CarService {
    private CarRepository cr;
    private CarDiscountRepository carDiscountRepository;
    private CarRateRepository crr;
    private CarTypeRepository ctr;
    private CarReservationRepository carReservationRepository;
    private CarServicePriceListRepository servicePriceListRepository;
    public CarServiceImpl(CarServicePriceListRepository carServicePriceListRepository, CarReservationRepository carReservationRepository, CarDiscountRepository carDiscountRepository, CarTypeRepository ctr, CarRateRepository crr, CarRepository cr)
    {
        this.carDiscountRepository = carDiscountRepository;
        this.ctr = ctr;
        this.crr=crr;
        this.cr=cr;
        this.carReservationRepository = carReservationRepository;
        this.servicePriceListRepository = carServicePriceListRepository;
    }

    @Override
    public String addCar(CarDTO car) {
        String ret = "ERROR";
        try {
            Car c = CarConverter.toEntity(car);
            CarType ct = ctr.findById(car.getTypeId()).get();
            c.setType(ct.getName());
            c.setNumOfSeats(ct.getSeats());
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

    @Override
    public List<CarDTO> getAvailableCars(String type, Integer from, Integer to, String start, String end) {
        try {



            List<Long> ids =  cr.getAvailableCars(type,from,to,start,end);
            List<Car> cars = new ArrayList<>();
            List<CarDTO> out = new ArrayList<>();
            for (Long id : ids)
                cars.add(cr.getCarByCarId(id));
            for (Car c:cars)
                out.add(CarConverter.fromEntity(c));
            return out;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }


    }

    @Override
    public void updateType(Integer seats, Long sId, Long id) {
     ctr.update(seats, sId, id);
    }

    @Override
    public List<CarTypeDTO> getTypes() {
        List<CarType> types = ctr.findAll();
        List<CarTypeDTO> out = new ArrayList<>();
        for(CarType c:types)
            out.add(CarTypeConverter.fromEntity(c));
        return  out;
    }

    @Override
    public boolean setDiscount(CarDiscountDTO carDiscountDTO) {
        carDiscountRepository.save(CarDiscountConverter.toEntity(carDiscountDTO));
        return true;
    }

    @Override
    public List<CarDiscountDTO> getAllCarDiscounts() {
        List<CarDiscount> carDiscounts = carDiscountRepository.findAll();
        List<CarReservation> carReservations = carReservationRepository.findAll();
        List<CarDiscountDTO> carDiscountDTOS = new ArrayList<>();
        for (CarDiscount carDiscount : carDiscounts) {
            List<CarReservation> carReservations1 = carReservationRepository.getThem(carDiscount.getStartDate(), carDiscount.getEndDate(), carDiscount.getCar().getCarId());
            if (carReservations1.size() == 0){
                CarServicePriceList carServicePriceList = servicePriceListRepository.findByCar_CarId(carDiscount.getCar().getCarId());
                carDiscount.setPrice(carServicePriceList.getPrice() - (carServicePriceList.getPrice() * carDiscount.getRateOfDiscount()/100));
                carDiscountDTOS.add(CarDiscountConverter.fromEntity(carDiscount));
            }
        }
        return carDiscountDTOS;
    }
}
