package com.bin448.backend.repository;

import com.bin448.backend.entity.PlaneSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<PlaneSeat, Long> {

}
