package com.bin448.backend.repository;

import com.bin448.backend.entity.HotelReservation;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelReservationRepository extends JpaRepository<HotelReservation, Long> {

    List<HotelReservation> getByRoom_Number(Long number);

    List<HotelReservation> getByRoom_Hotel_Id(Long hotelId);

    @Query(value = "select * from hotel_reservation hr right outer join hotel h on h.id = hr.hotel_id where hr.hotel_id = ?1 and user_username = ?2 and return_date < now()",nativeQuery = true)
    List<HotelReservation> getAllReservations(Long id,String username);


    @Query(value = "select * from hotel_reservation where room_id = ?1 and user_username = ?2 and return_date < now()",nativeQuery = true)
    List<HotelReservation> getAllReservationsRooms(Long id,String username);

    Boolean existsByUserUsernameAndHotel_Id(String username,Long hotelId);
    Boolean existsByUserUsernameAndRoom_Number(String username,Long id);


}
