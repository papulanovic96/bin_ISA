package com.bin448.backend.repository;


import com.bin448.backend.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarServiceRepository extends JpaRepository<CarService,Long> {


    CarService getCarServiceByCarServiceName(String name);

    CarService getCarServiceByCarServiceId(Long id);

    boolean existsByCarServiceName(String name);

    void deleteCarServiceByCarServiceId(Long id);
    @Query(value = "select * from car_service where deleted = 0",nativeQuery = true)
    List<CarService> findAll();

    @Transactional
    @Modifying
    @Query(value = "Update car_service set deleted = ?1 where car_service_id= ?2", nativeQuery = true)
    void logicalDeleting(Integer delete , Long id);

    @Transactional
    @Modifying
    @Query(value = "Update car_service set car_service_name = ?1, car_service_address = ?2, car_service_description = ?3, car_service_location = ?4 where car_service_id= ?5", nativeQuery = true)
    void modifyCarService(String name, String adress, String desc, String location, Long id);

    @Modifying
    @Transactional
    @Query(value = "Update car_service set avg_grade = ?1 where car_service_id = ?2", nativeQuery = true)
    void rateSelectedCarService(Double grade, Long regID);

    @Query(value = "select * from car_service where car_service_address like %?1% and car_service_name like %?2% and deleted = 0 order by avg_grade DESC",nativeQuery = true)
    List<CarService> search(String address,String name);
    @Transactional
    @Modifying
    @Query(value = "update car_service set profit = profit + ?1 where car_service_id = ?2",nativeQuery = true)
    void updateProfit(Integer profit,Long id);
}
