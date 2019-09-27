package com.bin448.backend.service;

import com.bin448.backend.converter.InvitationConverter;
import com.bin448.backend.entity.DTOentity.InvitationDTO;
import com.bin448.backend.entity.Invitation;
import com.bin448.backend.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepository ir;

    public void saveInivitation(Invitation inv) {
        ir.save(inv);
    }

    public List<InvitationDTO> findAll() {
        List<Invitation> invitations = ir.findAll();
        return InvitationConverter.fromEntityList(invitations, e -> InvitationConverter.fromEntity(e));
    }

    public void delete(InvitationDTO a) {
        Invitation ans = InvitationConverter.toEntity(a);
        ir.delete(ans);
    }
}
