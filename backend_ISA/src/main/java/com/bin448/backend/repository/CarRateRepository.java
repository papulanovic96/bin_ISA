package com.bin448.backend.repository;

import com.bin448.backend.entity.CarRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRateRepository extends JpaRepository<CarRate,Long> {
    List<CarRate> findAllByCar_RegID(String regID);
}
