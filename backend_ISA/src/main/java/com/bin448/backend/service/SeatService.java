package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.PlaneSeat;

import java.util.List;

public interface SeatService {
    String save(PlaneSeatDTO seat);

    String delete(PlaneSeatDTO seat);

    List<PlaneSeatDTO> findAll();

    PlaneSeatDTO findById(Long id);

    boolean modifySeat(PlaneSeatDTO seat);
}
