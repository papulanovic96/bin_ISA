package com.bin448.backend.repository;


import com.bin448.backend.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService,String> {


    CarService getCarServiceByCarServiceName(String name);
    CarService getCarServiceByCarServiceId(Long id);
    void deleteCarServiceByCarServiceName(String name);
    void deleteCarServiceByCarServiceId(Long id);
    @Transactional
    @Modifying
    @Query(value = "Update car_service set deleted = ?1 where car_service_name= ?2", nativeQuery = true)
    void logicalDeleting(boolean delete , String name);
}
