package com.bin448.backend.repository;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.LuggagePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    @Modifying
    @Query(value = "UPDATE isa.airline SET address = ?2, description = ?3, name = ?4, office_destination = ?5 WHERE id = ?1", nativeQuery = true)
    void modifyAirline(Long id, String address, String description, String name, String office_destination);
}