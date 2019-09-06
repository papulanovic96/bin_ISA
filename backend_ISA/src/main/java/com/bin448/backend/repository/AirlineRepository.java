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
    @Query(value = "UPDATE airline SET office_destination = ?6, name = ?5, luggage_price = ?4, description = ?3, address = ?2 WHERE id = ?1", nativeQuery = true)
    void modifyAirline(Long id, String address, String description, Collection<LuggagePrice> luggage_price, String name, String office_destination);
}