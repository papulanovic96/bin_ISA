package com.bin448.backend.service;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface CarService {
    String addCar(CarDTO car);
    String removeCar(String reg);
    CarDTO getCar(String reg);
    List<CarDTO> findAll(String serviceName);
    String modifyReserved(boolean r, String reg);
    String modifyCar(String model, String type, Integer year, Boolean convert, String regID);
    String rateCar(CarRateDTO cr);
    Double getAvgGrade(String regID);
    List<CarDTO> getAllCars();
}
