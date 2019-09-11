package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.PlaneSeat;

import java.util.List;

public interface SeatService {
    PlaneSeat save(PlaneSeatDTO seat);

    void delete(PlaneSeatDTO seat);

    List<PlaneSeatDTO> findAll();

    PlaneSeatDTO findById(Long id);

    boolean modifySeat(PlaneSeatDTO seat);
}
