package com.bin448.backend.repository;

import com.bin448.backend.entity.FastHotelReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FastHotelReservationRepository extends JpaRepository<FastHotelReservation, Long> {
    List<FastHotelReservation> findByDiscount_Id(Long discountId);
}
