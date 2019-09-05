package com.bin448.backend.converter;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class CarConverter extends AbstractConverter{
    private static CarServiceRepository cr;
    private static CarRepository cRep;
    public CarConverter(CarServiceRepository cr,CarRepository c){
        cRep = c;
        this.cr=cr;
    }

    public static CarDTO fromEntity(Car c){
        CarDTO cDTO = new CarDTO();
        cDTO.setModel(c.getModel());
        cDTO.setServiceName(c.getCarService().getCarServiceName());
        cDTO.setRegID(c.getRegID());
        cDTO.setConvertible(c.isConvertible());
        cDTO.setType(c.getType());
        cDTO.setYear(c.getYear());
        cDTO.setDeleted(c.getDeleted());
        return cDTO;
    }

    public static Car toEntity(CarDTO cDTO){
        Car car = new Car();
        car.setRegID(cDTO.getRegID());
        car.setModel(cDTO.getModel());
        car.setReserved(false);
        car.setCarService(cr.getCarServiceByCarServiceName(cDTO.getServiceName()));
        car.setModel(cDTO.getModel());
        car.setConvertible(cDTO.isConvertible());
        car.setType(cDTO.getType());
        car.setYear(cDTO.getYear());
        car.setDeleted(cDTO.getDeleted());
        return car;
    }


}
