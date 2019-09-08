package com.bin448.backend.converter;

import com.bin448.backend.entity.CarServiceRate;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.repository.CarServiceRateRepository;
import com.bin448.backend.repository.CarServiceRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CarServiceRateConverter {

    private static CarServiceRepository csR;
    private static UserRepository ur;

    public CarServiceRateConverter(UserRepository ur,CarServiceRepository csr){
        this.csR = csr;
        this.ur = ur;
    }

    public static CarServiceRate toEntity(CarServiceRateDTO crDTO){
        CarServiceRate  csRate = new CarServiceRate();
        csRate.setCarService(csR.getCarServiceByCarServiceId(crDTO.getServiceID()));
        csRate.setUser(ur.getUserById(crDTO.getUserID()));
        csRate.setId(crDTO.getId());
        csRate.setRate(crDTO.getRate());
        return csRate;
    }
    public static CarServiceRateDTO fromEntity(CarServiceRate csr){
        CarServiceRateDTO c = new CarServiceRateDTO();
        c.setId(csr.getId());
        c.setRate(csr.getRate());
        c.setServiceID(csr.getCarService().getCarService_id());
        c.setUserID(csr.getUser().getId());
        return c;
    }
}
