package com.bin448.backend.repository;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneTicketRepository extends JpaRepository<PlaneTicket, Long> {

    @Modifying
    @Query(value = "UPDATE tickets SET reserved_by_id = ?2 WHERE id = ?1", nativeQuery = true)
    void reserveDiscountTicket(Long id, User reservedBy);

    @Query(value = "SELECT * FROM tickets t right outer join flight f on t.flight_id = f.id where f.date_and_time_landing<now() and f.id = ?1 and t.reserved_by_id = ?2",nativeQuery = true)
    List<PlaneTicket> getAllTickets(Long flightId, Long userId);

    @Query(value = "SELECT * FROM tickets t right outer join flight f on t.airline_id = f.airline_id where f.date_and_time_landing<now() and t.airline_id = ?1 and t.reserved_by_id = ?2",nativeQuery = true)
    List<PlaneTicket> getAllTicketsForAirline(Long airlineId,Long userId);
}
