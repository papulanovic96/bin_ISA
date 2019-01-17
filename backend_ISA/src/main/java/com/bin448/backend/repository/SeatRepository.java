package com.bin448.backend.repository;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.entity.PlaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<PlaneSeat, Long> {

    @Modifying
    @Query (value = "UPDATE seats SET ticket_id = ?3, airline_id = ?2 WHERE seat_id = ?1", nativeQuery = true)
    void modifySeat(Long id, Airline airline, PlaneTicket planeTicket);
}
