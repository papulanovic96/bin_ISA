package com.bin448.backend.service;

import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        PlaneSeat newSeat = findById(seat.getSeatId());
        seatRepository.deleteById(newSeat.getSeatId());
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
    @Transactional
    @Override
    public boolean modifySeat(PlaneSeat seat) {
        PlaneSeat newSeat = findById(seat.getSeatId());
        if(newSeat == null) {
            return false;
        } else if (newSeat.getReserved()) {
            return false;
        }
            seatRepository.modifySeat(newSeat.getSeatId(), newSeat.getAirline(), newSeat.getTicket());
            return true;

    }
}
