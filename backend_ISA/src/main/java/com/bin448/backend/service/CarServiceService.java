package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.CarServiceDTO;


public interface CarServiceService {

    void addCarService(CarServiceDTO cs);

    void removeCarService(String ime);

    void removeCarService(Long id);

    CarServiceDTO findCarService(String name);

}
