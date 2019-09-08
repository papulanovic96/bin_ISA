package com.bin448.backend.service;

import com.bin448.backend.converter.CarConverter;
import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private CarRepository cr;

    public CarServiceImpl(CarRepository cr) {
        this.cr = cr;
    }

    @Override
    public void addCar(CarDTO car) {
        Car c = CarConverter.toEntity(car);
        cr.save(c);
    }

    @Override
    public void removeCar(String reg) {
        cr.deleteCarByRegID(reg);
    }

    @Override
    public CarDTO getCar(String reg) {
        CarDTO c = CarConverter.fromEntity(cr.getCarByRegID(reg));
        return c;
    }

    @Override
    public List<CarDTO> findAll(Long id) {
        List<Car> auti = cr.findAllByCarService_CarServiceId(id);
        List<CarDTO> cardto = new ArrayList<>();
        for (int i = 0; i < auti.size(); i++)
            cardto.add(CarConverter.fromEntity(auti.get(i)));
        return cardto;
    }
}
