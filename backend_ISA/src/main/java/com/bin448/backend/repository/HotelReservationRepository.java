package com.bin448.backend.repository;

import com.bin448.backend.entity.HotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {

    List<HotelReservation> getByRoom_Number(Long number);

}
