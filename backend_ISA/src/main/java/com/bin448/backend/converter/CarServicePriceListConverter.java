package com.bin448.backend.converter;

import com.bin448.backend.entity.CarServicePriceList;
import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.repository.CarRepository;
import com.bin448.backend.repository.CarServicePriceListRepository;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class CarServicePriceListConverter extends AbstractConverter {
private static CarServiceRepository csp;
private static CarRepository cr;
    public CarServicePriceListConverter(CarRepository cr,CarServiceRepository csp)

    {
        this.cr = cr;
        this.csp = csp;
    }

    public static CarServicePriceList toEntity(CarServicePriceListDTO cspl){
        CarServicePriceList cspp = new CarServicePriceList();
        cspp.setCarS(csp.getCarServiceByCarServiceId(cspl.getCarServiceId()));
        cspp.setIdCarServicePriceList(cspl.getId());
        cspp.setCar(cr.getCarByCarId(cspl.getCarId()));
        cspp.setPrice(cspl.getPrice());
        return cspp;
    }

    public static CarServicePriceListDTO fromEntity(CarServicePriceList cspl){
        CarServicePriceListDTO cspp = new CarServicePriceListDTO();
        cspp.setCarServiceName(cspl.getCarS().getCarServiceName());
        cspp.setCarServiceId(cspl.getCarS().getCarService_id());
        cspp.setId(cspl.getIdCarServicePriceList());
        cspp.setCarId(cspl.getCar().getCarId());
        cspp.setCarModel(cspl.getCar().getModel());
        cspp.setCarREGID(cspl.getCar().getRegID());
        cspp.setCarYear(cspl.getCar().getYear());
        cspp.setPrice(cspl.getPrice());
        return cspp;
    }

}
