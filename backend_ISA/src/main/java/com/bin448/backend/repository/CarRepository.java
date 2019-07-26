package com.bin448.backend.repository;

import com.bin448.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
    Car getCarByRegID(String regId);
    void deleteCarByRegID(String regId);
    List<Car> findAllByCarService_CarServiceId(Long id);
    void deleteAllByCarService_CarServiceId(Long id);
}
