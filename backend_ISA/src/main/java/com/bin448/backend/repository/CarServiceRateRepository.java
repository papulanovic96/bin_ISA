package com.bin448.backend.repository;

import com.bin448.backend.entity.CarServiceRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarServiceRateRepository extends JpaRepository<CarServiceRate,Long> {
    List<CarServiceRate> findAllByCarService_CarServiceName(String name);
}
