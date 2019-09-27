package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.PlaneTicketDTO;
import com.bin448.backend.entity.PlaneTicket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlaneTicketService {
    String save(PlaneTicketDTO newTicket);
    List<PlaneTicketDTO> findAll();
    boolean reserve(PlaneTicketDTO pto);
}
