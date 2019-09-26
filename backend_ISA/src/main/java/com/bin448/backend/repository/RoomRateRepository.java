package com.bin448.backend.repository;

import com.bin448.backend.entity.HotelRate;
import com.bin448.backend.entity.RoomRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRateRepository extends JpaRepository<RoomRate,Long> {
    List<RoomRate> findAllByUser_IdAndRoom_Number(Long userID, Long nnumber);

}
