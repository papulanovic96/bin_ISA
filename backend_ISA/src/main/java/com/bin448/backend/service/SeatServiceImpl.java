package com.bin448.backend.service;

import com.bin448.backend.converter.PlaneSeatConverter;
import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    public SeatRepository seatRepository;

    @Override
    public PlaneSeat save(PlaneSeatDTO seat) {
        PlaneSeat seatNew = PlaneSeatConverter.toEntity(seat);
        return seatRepository.save(seatNew);
    }

    @Override
    public void delete(PlaneSeatDTO seat) {
        PlaneSeat newSeat = PlaneSeatConverter.toEntity(seat);
        seatRepository.deleteById(newSeat.getSeatId());
    }

    @Override
    public List<PlaneSeatDTO> findAll() {
        List<PlaneSeat> planeSeatList = seatRepository.findAll();
        List<PlaneSeatDTO> planeSeatDTOList = PlaneSeatConverter.fromEntityList(planeSeatList, e -> PlaneSeatConverter.fromEntity(e));
        return planeSeatDTOList;
    }

    @Override
    public PlaneSeatDTO findById(Long id) {
        PlaneSeat newSeat = seatRepository.findById(id).orElse(null);
        PlaneSeatDTO newSeatDTO = PlaneSeatConverter.fromEntity(newSeat);
        return newSeatDTO;
    }
    @Transactional
    @Override
    public boolean modifySeat(PlaneSeatDTO seat) {
        PlaneSeat newSeat = PlaneSeatConverter.toEntity(seat);
        PlaneSeat newSeatForUse = seatRepository.findById(newSeat.getSeatId()).orElse(null);
        if(newSeatForUse == null) {
            return false;
        } else if (newSeatForUse.getReserved()) {
            return false;
        }
            seatRepository.modifySeat(newSeat.getSeatId(), newSeat.getAirline(), newSeat.getTicket());
            return true;

    }
}
