package com.bin448.backend.service;

import com.bin448.backend.converter.PlaneSeatConverter;
import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class SeatServiceImpl implements SeatService {
    @Autowired
    public SeatRepository seatRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String save(PlaneSeatDTO seat) {
        PlaneSeat seatNew = PlaneSeatConverter.toEntity(seat);
        seatRepository.save(seatNew);
        return "Seat added!";
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String delete(PlaneSeatDTO seat) {
        PlaneSeat newSeat = PlaneSeatConverter.toEntity(seat);
        seatRepository.deleteById(newSeat.getSeatId());
        return "Seat deleted!";
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaneSeatDTO> findAll() {
        List<PlaneSeat> planeSeatList = seatRepository.findAll();
        List<PlaneSeatDTO> planeSeatDTOList = PlaneSeatConverter.fromEntityList(planeSeatList, e -> PlaneSeatConverter.fromEntity(e));
        return planeSeatDTOList;
    }

    @Override
    @Transactional(readOnly = true)
    public PlaneSeatDTO findById(Long id) {
        PlaneSeat newSeat = seatRepository.findById(id).orElse(null);
        PlaneSeatDTO newSeatDTO = PlaneSeatConverter.fromEntity(newSeat);
        return newSeatDTO;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public boolean modifySeat(PlaneSeatDTO seat) {
        PlaneSeat newSeat = PlaneSeatConverter.toEntity(seat);
        PlaneSeat newSeatForUse = seatRepository.findById(newSeat.getSeatId()).orElse(null);
        if (newSeatForUse == null) {
            return false;
        } else if (newSeatForUse.getReserved()) {
            return false;
        }
        seatRepository.modifySeat(newSeat.getSeatId(), newSeat.getReserved(), newSeat.getAirline(), newSeat.getTicket());
        return true;

    }
}
