package com.bin448.backend.repository;

import com.bin448.backend.entity.HotelRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelRateRepository extends JpaRepository<HotelRate,Long> {

    List<HotelRate> findAllByUser_IdAndHotel_Id(Long userID,Long hotelID);
}
