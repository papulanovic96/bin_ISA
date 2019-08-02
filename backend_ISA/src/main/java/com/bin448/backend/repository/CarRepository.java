package com.bin448.backend.repository;

import com.bin448.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
    Car getCarByRegID(String regId);
    void deleteCarByRegID(String regId);
    void deleteAllByCarService_CarServiceId(Long id);
    Car findByCarService_CarServiceName(String name);
    List<Car> findAllByCarService_CarServiceName(String name);
    @Modifying
    @Transactional
    @Query(value = "Update cars set reserved = ?1 where regID = ?2", nativeQuery = true)
    void modifyReserved(boolean reserved, String regID);
    @Modifying
    @Transactional
    @Query(value = "Update cars set model = ?1, typeC= ?2, year = ?3, convertible = ?4 where regID = ?5",nativeQuery = true)
    void modifyCar(String model, String type, Integer year, Boolean convertible, String regID);

}
