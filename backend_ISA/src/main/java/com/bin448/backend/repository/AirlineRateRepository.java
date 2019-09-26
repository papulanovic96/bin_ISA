package com.bin448.backend.repository;

import com.bin448.backend.entity.AirlineRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRateRepository extends JpaRepository<AirlineRate,Long> {
    List<AirlineRate> findAllByUser_IdAndAirline_Id(Long userId,Long airlineId);
}
