package com.bin448.backend.repository;

import com.bin448.backend.entity.PlaneTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneTicketRepository extends JpaRepository<PlaneTicket, Long> {
}
