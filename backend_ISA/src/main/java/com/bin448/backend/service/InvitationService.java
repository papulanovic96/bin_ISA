package com.bin448.backend.service;

import com.bin448.backend.converter.InvitationConverter;
import com.bin448.backend.entity.DTOentity.InvitationDTO;
import com.bin448.backend.entity.Invitation;
import com.bin448.backend.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvitationService {

    @Autowired
    private InvitationRepository ir;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void saveInivitation(Invitation inv) {
        ir.save(inv);
    }

    @Transactional(readOnly = true)
    public List<InvitationDTO> findAll() {
        List<Invitation> invitations = ir.findAll();
        return InvitationConverter.fromEntityList(invitations, e -> InvitationConverter.fromEntity(e));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void delete(InvitationDTO a) {
        Invitation ans = InvitationConverter.toEntity(a);
        ir.delete(ans);
    }
}
