package com.bin448.backend.repository;

import com.bin448.backend.entity.CarRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRateRepository extends JpaRepository<CarRate,Long> {
 //   List<CarRate> findAllByCar_RegID(String regID);
    List<CarRate> findAllByCar_CarId(Long id);
   @Query(value = "select avg(rate) from car_rate where car_id = ?1",nativeQuery = true)
    Double getAvgRate(Long carID);
 //   boolean existsByCar_CarIdAndUser_Id(Long carId, Long userId);
    List<CarRate> findAllByCar_CarIdAndUser_Id(Long carId,Long userId);
}
