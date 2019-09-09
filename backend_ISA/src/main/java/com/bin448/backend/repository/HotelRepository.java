package com.bin448.backend.repository;

import com.bin448.backend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByIdAndDeleted(Long id, Boolean deleted);

    List<Hotel> findByDeleted(Boolean deleted);

}
