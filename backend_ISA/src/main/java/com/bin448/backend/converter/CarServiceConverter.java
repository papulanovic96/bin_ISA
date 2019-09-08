package com.bin448.backend.converter;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarService;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.repository.CarRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceConverter {
    private static CarRepository carR;

    public CarServiceConverter(CarRepository cr) {
        carR = cr;
    }

    public static CarServiceDTO fromEntity(/*@org.jetbrains.annotations.NotNull*/ CarService cs) {
        CarServiceDTO csDTO = new CarServiceDTO();
        List<String> listaReg = new ArrayList<>();
        csDTO.setCarServiceAddress(cs.getCarServiceAddress());
        csDTO.setCarServiceDescription(cs.getCarServiceDescription());
        csDTO.setCarServiceMenu(cs.getCarServiceMenu());
        csDTO.setCarService_id(cs.getCarService_id());
        csDTO.setCarServiceName(cs.getCarServiceName());
        for (Car c : cs.getCarsCollection()) {
            listaReg.add(c.getRegID());

        }
        csDTO.setCarsCollection(listaReg);
        return csDTO;


    }

    public static CarService toEntity(/*@org.jetbrains.annotations.NotNull*/ CarServiceDTO csDTO) {
        CarService cs = new CarService();
        cs.setAvgGrade(csDTO.getAvgGrade());
        List<Car> listaAuta = new ArrayList<>();
        Car c = new Car();
        List<String> list = csDTO.getCarsCollection();
        if (list != null && list.size() > 0) {
            for (String reg : list) {
                Car car = carR.getCarByRegID(reg);
                listaAuta.add(car);
            }
            cs.setCarsCollection(listaAuta);

        } else
            cs.setCarsCollection(listaAuta);
        cs.setCarService_id(csDTO.getCarService_id());
        cs.setCarServiceAddress(csDTO.getCarServiceAddress());
        cs.setCarServiceDescription(csDTO.getCarServiceDescription());
        cs.setCarServiceMenu(csDTO.getCarServiceMenu());
        cs.setCarServiceLocation(csDTO.getCarServiceLocation());
        cs.setCarServiceName(csDTO.getCarServiceName());
        return cs;
    }
}
