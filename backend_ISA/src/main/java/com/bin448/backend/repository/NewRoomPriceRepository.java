package com.bin448.backend.repository;

import com.bin448.backend.entity.NewRoomPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewRoomPriceRepository extends JpaRepository<NewRoomPrice, Long> {
    List<NewRoomPrice> findByRoom_Number(Long roomId);
}
