package com.bin448.backend.converter;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class CarConverter extends AbstractConverter {
    private static CarServiceRepository cr;

    public CarConverter(CarServiceRepository cr) {
        CarConverter.cr = cr;
    }

    public static CarDTO fromEntity(Car c) {
        CarDTO cDTO = new CarDTO();
        cDTO.setAvgGrade(c.getAvgGrade());
        cDTO.setIdService(c.getCarService().getCarService_id());
        cDTO.setCarId(c.getCarId());
        cDTO.setRegID(c.getRegID());
        return cDTO;
    }

    public static Car toEntity(CarDTO cDTO) {
        Car car = new Car();
        car.setRegID(cDTO.getRegID());
        car.setAvgGrade(cDTO.getAvgGrade());
        car.setCarId(cDTO.getCarId());
        car.setReserved(false);
        car.setCarService(cr.getCarServiceByCarServiceId(cDTO.getIdService()));
        return car;
    }


}
