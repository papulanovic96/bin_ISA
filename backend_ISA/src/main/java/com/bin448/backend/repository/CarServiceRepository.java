package com.bin448.backend.repository;


import com.bin448.backend.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService,String> {

    CarService getCarServiceByCarServiceName(String name);
     CarService getCarServiceByCarServiceId(Long id);
}
