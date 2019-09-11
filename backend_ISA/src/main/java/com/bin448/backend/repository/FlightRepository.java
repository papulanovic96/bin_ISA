package com.bin448.backend.repository;

import com.bin448.backend.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM isa.flight AS r WHERE (r.date_and_time_take_off <= ?1 AND r.date_and_time_landing >= ?1) OR (r.date_and_time_take_off < ?2 AND r.date_and_time_landing >= ?2 ) OR (?1 <= r.date_and_time_take_off AND ?2 >= r.date_and_time_take_off) OR ( r.from_destination = ?3) OR ( r.to_destination = ?4);", nativeQuery = true)
    List<Flight> checkAvailability(String startDate, String endDate, String flightFrom, String flightTo);
}
