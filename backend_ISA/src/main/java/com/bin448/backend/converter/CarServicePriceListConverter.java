package com.bin448.backend.converter;

import com.bin448.backend.entity.CarServicePriceList;
import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.repository.CarServicePriceListRepository;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class CarServicePriceListConverter extends AbstractConverter {
private static CarServiceRepository csp;

    public CarServicePriceListConverter(CarServiceRepository csp){
        this.csp = csp;
    }

    public static CarServicePriceList toEntity(CarServicePriceListDTO cspl){
        CarServicePriceList cspp = new CarServicePriceList();
        cspp.setCarS(csp.getCarServiceByCarServiceName(cspl.getCarServiceName()));
        cspp.setIdCarServicePriceList(cspl.getId());
        cspp.setName(cspl.getName());
        cspp.setPrice(cspl.getPrice());
        return cspp;
    }

    public static CarServicePriceListDTO fromEntity(CarServicePriceList cspl){
        CarServicePriceListDTO cspp = new CarServicePriceListDTO();
        cspp.setCarServiceName(cspl.getCarS().getCarServiceName());
        cspp.setId(cspl.getIdCarServicePriceList());
        cspp.setName(cspl.getName());
        cspp.setPrice(cspl.getPrice());
        return cspp;
    }

}
