package com.bin448.backend.service;

import com.bin448.backend.converter.CarServiceConverter;
import com.bin448.backend.entity.CarService;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.repository.CarServiceRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceServiceImpl implements CarServiceService {

    private CarServiceRepository csr;
    public CarServiceServiceImpl(CarServiceRepository csr){
        this.csr=csr;
    }




    @Override
    public void addCarService(CarServiceDTO cs) {
    CarService cService = CarServiceConverter.toEntity(cs);
    csr.save(cService);
    }

    @Override
    public void removeCarService(String ime) {
        csr.deleteCarServiceByCarServiceName(ime);
    }

    @Override
    public void removeCarService(Long id) {
        csr.deleteCarServiceByCarServiceId(id);
    }

    @Override
    public CarServiceDTO findCarService(String name) {
        return CarServiceConverter.fromEntity( csr.getCarServiceByCarServiceName(name));
    }

}
