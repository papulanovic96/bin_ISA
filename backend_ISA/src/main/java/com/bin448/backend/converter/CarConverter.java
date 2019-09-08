package com.bin448.backend.converter;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class CarConverter extends AbstractConverter {
    private static CarServiceRepository cr;

    private static CarRepository cRep;
    public CarConverter(CarServiceRepository cr,CarRepository c){
        cRep = c;
        this.cr=cr;

    }

    public static CarDTO fromEntity(Car c) {
        CarDTO cDTO = new CarDTO();
        cDTO.setNos(c.getNumOfSeats());
        cDTO.setModel(c.getModel());
        cDTO.setServiceId(c.getCarService().getCarService_id());
        cDTO.setRegID(c.getRegID());
        cDTO.setConvertible(c.isConvertible());
        cDTO.setType(c.getType());
        cDTO.setYear(c.getYear());
        cDTO.setServiceName(c.getCarService().getCarServiceName());
        cDTO.setId(c.getCarId());
        cDTO.setAvgGrade(c.getAvgGrade());
        cDTO.setDeleted(c.getDeleted());
        cDTO.setReserved(c.isReserved());
        return cDTO;
    }

    public static Car toEntity(CarDTO cDTO) {
        Car car = new Car();
        car.setAvgGrade(cDTO.getAvgGrade());
        car.setRegID(cDTO.getRegID());
        car.setModel(cDTO.getModel());
        car.setCarId(cDTO.getId());
        car.setNumOfSeats(cDTO.getNos());
        car.setReserved(cDTO.getReserved());
        car.setReserved(false);
        car.setCarService(cr.getCarServiceByCarServiceId(cDTO.getServiceId()));
        car.setModel(cDTO.getModel());
        car.setConvertible(cDTO.isConvertible());
        car.setType(cDTO.getType());
        car.setYear(cDTO.getYear());
        car.setDeleted(cDTO.getDeleted());
        return car;
    }


}
