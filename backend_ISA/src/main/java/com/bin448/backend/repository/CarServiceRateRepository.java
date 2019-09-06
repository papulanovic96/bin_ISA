package com.bin448.backend.repository;

import com.bin448.backend.entity.CarServiceRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarServiceRateRepository extends JpaRepository<CarServiceRate,Long> {
    List<CarServiceRate> findAllByCarService_CarServiceName(String name);
    List<CarServiceRate> findAllByCarService_CarServiceId(Long id);
    boolean existsByCarService_CarServiceIdAndUser_Id(Long serviceId, Long userId);
    List<CarServiceRate> findAllByUser_IdAndCarService_CarServiceId(Long userID,Long serviceID);
    @Query(value = "select avg(rate) from car_service_rate where car_service_id = ?1",nativeQuery = true)
    Double getAvgRate(Long carID);

}
