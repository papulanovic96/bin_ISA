package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.Room;

public interface RoomService {

    void addRoom(RoomDTO roomDTO);

    void removeRoom(Long id);

    void changeRoom(RoomDTO roomDTO, Long id);

    Room findRoomById(Long id);
}
