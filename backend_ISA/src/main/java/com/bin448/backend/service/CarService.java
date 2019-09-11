package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.entity.DTOentity.CarTypeDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;
import java.util.List;

public interface CarService {

    String addCar(CarDTO car);
   // boolean checkReservation(Long carId,Long userId);
    String removeCar(String reg);
    List<CarDTO> search(String model,String type,Integer from,Integer to,Integer nos);
    CarDTO getCar(String reg);
    List<CarDTO> findAll(String serviceName);
    String modifyReserved(boolean r, Long id);
    String modifyCar(String model, String type, Integer year, Boolean convert, String regID,Long serviceID,Long carID );
    String rateCar(CarRateDTO cr);
 //   Double getAvgGrade(String regID);
    List<CarDTO> getAllCars();
    boolean isUserRated(Long id,Long userId);
    List<CarDTO> getAllReservedCars(Long userID);
    List<CarDTO> getAvailableCars(String type, Integer from, Integer to, String start, String end);
    void updateType(Integer seats,Long sId,Long id);
    List<CarTypeDTO> getTypes();


}
