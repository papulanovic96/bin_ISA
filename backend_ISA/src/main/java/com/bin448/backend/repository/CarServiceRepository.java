package com.bin448.backend.repository;


import com.bin448.backend.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService,String> {


    CarService getCarServiceByCarServiceName(String name);
    CarService getCarServiceByCarServiceId(Long id);
    void deleteCarServiceByCarServiceName(String name);
    void deleteCarServiceByCarServiceId(Long id);
}
