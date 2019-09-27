package com.bin448.backend.repository;

import com.bin448.backend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByIdAndDeleted(Long id, Boolean deleted);

    List<Hotel> findByDeleted(Boolean deleted);

    @Query(value = "select * from hotel where name like %:name%", nativeQuery = true)
    List<Hotel> searchHotelName(@Param("name") String name);

    @Query(value = "SELECT distinct * FROM hotel h right outer join hotel_reservation hr on hr.hotel_id = h.id where h.deleted = 0 and (hr.return_date < ?1 and hr.arrival_date < ?1) or (hr.return_date > ?2 and hr.arrival_date > ?2)", nativeQuery = true)
    List<Hotel> searchHotelByDate(@Param("arrival") String arrival,@Param("end") String end);

}
