package com.bin448.backend.converter;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class CarConverter extends AbstractConverter {
    public static CarServiceRepository cr;

    public static CarRepository cRep;

    public CarConverter(CarServiceRepository cr, CarRepository c) {
        cRep = c;
        this.cr = cr;

    }

    public static CarDTO fromEntity(Car c) {
        CarDTO cDTO = new CarDTO();
        cDTO.setModel(c.getModel());
        cDTO.setServiceId(c.getCarService().getCarService_id());
        cDTO.setRegID(c.getRegID());
        cDTO.setTypeC(c.getType());
        cDTO.setNos(c.getNumOfSeats());
        cDTO.setConvertible(c.isConvertible());
        cDTO.setYear(c.getYear());
        cDTO.setServiceName(c.getCarService().getCarServiceName());
        cDTO.setId(c.getCarId());
        cDTO.setAvgGrade(c.getAvgGrade());
        cDTO.setDeleted(c.getDeleted());
        cDTO.setReserved(c.isReserved());
        return cDTO;
    }

    public static Car toEntity(CarDTO cDTO) {
        if(cDTO == null)
            return new Car();
        Car car = new Car();
        car.setAvgGrade(cDTO.getAvgGrade());
        car.setRegID(cDTO.getRegID());
        car.setModel(cDTO.getModel());
        car.setCarId(cDTO.getId());
        car.setType(cDTO.getTypeC());
        car.setReserved(cDTO.getReserved());
        car.setReserved(cDTO.getReserved());
        if (cr == null)
            car.setCarService(null);
        else
        car.setCarService(cr.getCarServiceByCarServiceId(cDTO.getServiceId()));
        car.setModel(cDTO.getModel());
        car.setConvertible(cDTO.isConvertible());
        car.setYear(cDTO.getYear());
        car.setDeleted(cDTO.getDeleted());
        return car;
    }


}
