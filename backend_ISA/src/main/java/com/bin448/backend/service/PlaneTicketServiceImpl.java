package com.bin448.backend.service;


import com.bin448.backend.converter.PlaneSeatConverter;
import com.bin448.backend.converter.PlaneTicketConverter;
import com.bin448.backend.entity.DTOentity.PlaneTicketDTO;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.repository.PlaneTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaneTicketServiceImpl implements PlaneTicketService{

    @Autowired
    private PlaneTicketRepository planeTicketRepository;

    @Override
    public String save(PlaneTicketDTO newTicket) {
        planeTicketRepository.save(PlaneTicketConverter.toEntity(newTicket));
        return "Ticket added!";
    }

    @Override
    public List<PlaneTicketDTO> findAll() {
        List<PlaneTicket> newList = planeTicketRepository.findAll();
        return PlaneTicketConverter.fromEntityList(newList, e-> PlaneTicketConverter.fromEntity(e));
    }

    @Transactional
    @Override
    public boolean reserve(PlaneTicketDTO pto) {
        PlaneTicket ticketNew = PlaneTicketConverter.toEntity(pto);
        PlaneTicket forUse = planeTicketRepository.findById(ticketNew.getId()).orElse(null);
        if(forUse == null) {
            return false;
        } else {
            planeTicketRepository.reserveDiscountTicket(ticketNew.getId(), ticketNew.getReservedBy());
            return true;
        }
    }
}
