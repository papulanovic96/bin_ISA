package com.bin448.backend.service;

import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public PlaneSeat save(PlaneSeat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void delete(PlaneSeat seat) {
        seatRepository.deleteById(seat.getSeatId());
    }

    @Override
    public List<PlaneSeat> findAll() {
        List<PlaneSeat> planeSeatList = seatRepository.findAll();
        return planeSeatList;
    }

    @Override
    public PlaneSeat findById(Long id) {
        return seatRepository.findById(id).orElse(null);
    }
}
