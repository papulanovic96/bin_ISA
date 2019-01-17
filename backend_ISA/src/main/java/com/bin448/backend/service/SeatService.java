package com.bin448.backend.service;

import com.bin448.backend.entity.PlaneSeat;

import java.util.List;

public interface SeatService {
    PlaneSeat save(PlaneSeat seat);
    void delete(PlaneSeat seat);
    List<PlaneSeat> findAll();
    PlaneSeat findById(Long id);
    boolean modifySeat(PlaneSeat seat);
}
