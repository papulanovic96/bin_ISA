package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.Room;

import java.util.List;

public interface RoomService {

    void addRoom(RoomDTO roomDTO);

    void removeRoom(Long id);

    void changeRoom(RoomDTO roomDTO, Long id);

    void changeRoomReserved(RoomDTO roomDTO, Long id);

    List<RoomDTO> findAll();

    Room findRoomById(Long id);

    RoomDTO findRoomDTOById(Long id);

    List<RoomDTO> findRoomsByHotelId(Long hotelId);

    boolean checkIfThereIsAvailableRoom(RoomDTO room, HotelReservationDTO hotelReservationDTO);

    List<List<RoomDTO>> findRoomsFromReservation(List<RoomDTO> rooms, HotelReservationDTO hotelReservationDTO);

    boolean existReservationWithRoomId(Long roomId);
}
