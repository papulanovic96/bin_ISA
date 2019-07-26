package com.bin448.backend.service;

import com.bin448.backend.entity.CarService;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



public interface CarServiceService {

    void addCarService(CarServiceDTO cs);
    void removeCarService(String ime);
    void removeCarService(Long id);
    CarServiceDTO findCarService(String name);

}
