package com.bin448.backend.repository;

import com.bin448.backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByNumberAndDeleted(Long id, Boolean deleted);

    List<Room> findByDeleted(Boolean deleted);

    List<Room> findByHotel_IdAndDeleted(Long hotelId, Boolean deleted);

    List<Room> findByHotel_IdAndRoomType_IdAndDeleted(Long hotelId, Long typeId, Boolean deleted);

}
