package com.bin448.backend.converter;

import com.bin448.backend.entity.CarServiceRate;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.repository.CarServiceRateRepository;
import com.bin448.backend.repository.CarServiceRepository;
import org.springframework.stereotype.Component;

@Component
public class CarServiceRateConverter {

    private static CarServiceRepository csR;

    public CarServiceRateConverter(CarServiceRepository csr){
        this.csR = csr;
    }

    public static CarServiceRate toEntity(CarServiceRateDTO crDTO){
        CarServiceRate  csRate = new CarServiceRate();
        csRate.setCarService(csR.getCarServiceByCarServiceName(crDTO.getCarServiceName()));
        csRate.setId(crDTO.getId());
        csRate.setRate(crDTO.getRate());
        return csRate;
    }
    public static CarServiceRateDTO fromEntity(CarServiceRate csr){
        CarServiceRateDTO c = new CarServiceRateDTO();
        c.setCarServiceName(csr.getCarService().getCarServiceName());
        c.setId(csr.getId());
        c.setRate(csr.getRate());
        return c;
    }
}
