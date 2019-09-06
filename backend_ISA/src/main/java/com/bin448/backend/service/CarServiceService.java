package com.bin448.backend.service;

import com.bin448.backend.entity.CarService;
import com.bin448.backend.entity.CarServicePriceList;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CarServiceService {

    String addCarService(CarServiceDTO cs);
    String logicRemoveCarService(Long id);
    String removeCarService(Long id);
    CarServicePriceListDTO getItem(Long id);
    String addItem(CarServicePriceList cs);
    String deleteItem(Long id);
    CarServiceDTO findCarService(String name);
    List<CarServicePriceListDTO> getAllItems(String name);
    String changeItem(String ime,Double cena,Long id);
    String carServiceRate(CarServiceRateDTO csr);
    Double getAvgGrade(String name);
    List<CarServiceDTO> getAll();
    String modifyService(String name, String adress, String description, String location, Long id);
    boolean isUserAbleToRateService(Long userID,Long serviceID);
    List<CarServiceDTO> search(String address,String name);
}
