package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.CarDTO;

import java.util.List;

public interface CarService {
    void addCar(CarDTO car);

    void removeCar(String reg);

    CarDTO getCar(String reg);

    List<CarDTO> findAll(Long id);
}
