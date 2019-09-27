package com.bin448.backend.service;

import com.bin448.backend.converter.FriendshipConverter;
import com.bin448.backend.entity.DTOentity.FrendshipDTO;
import com.bin448.backend.entity.Friendship;
import com.bin448.backend.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void save(Friendship newFriendship) {
        friendshipRepository.save(newFriendship);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void delete(Friendship newFriendship) {
        friendshipRepository.delete(newFriendship);
    }

    @Transactional(readOnly = true)
    public List<FrendshipDTO> findAll() {
        List<Friendship> newList = friendshipRepository.findAll();
        List<FrendshipDTO> newListDTO = FriendshipConverter.fromEntityList(newList, e -> FriendshipConverter.fromEntity(e));
        return newListDTO;
    }

    @Transactional(readOnly = true)
    public List<Friendship> findAllEntities() {
        return friendshipRepository.findAll();
    }

    public void modifyIT(String receiver, String sender){
        friendshipRepository.modifyIT(receiver, sender);
    }
}
