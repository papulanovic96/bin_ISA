package com.bin448.backend.repository;

import com.bin448.backend.entity.FlightRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRateRepository extends JpaRepository<FlightRate,Long> {
    List<FlightRate> findAllByUser_IdAndFlight_Id(Long userID, Long flightID);

}
