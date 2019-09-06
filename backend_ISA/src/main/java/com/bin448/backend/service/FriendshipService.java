package com.bin448.backend.service;

import com.bin448.backend.converter.FriendshipConverter;
import com.bin448.backend.entity.DTOentity.FrendshipDTO;
import com.bin448.backend.entity.Friendship;
import com.bin448.backend.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public void save(Friendship newFriendship) {
        friendshipRepository.save(newFriendship);
    }

    public List<FrendshipDTO> findAll() {
        List<Friendship> newList = friendshipRepository.findAll();
        List<FrendshipDTO> newListDTO = FriendshipConverter.fromEntityList(newList, e -> FriendshipConverter.fromEntity(e));
        return newListDTO;
    }

    public List<Friendship> findAllEntities() {
        return friendshipRepository.findAll();
    }

    public void modifyIT(String receiver, String sender){
        friendshipRepository.modifyIT(receiver, sender);
    }
}
