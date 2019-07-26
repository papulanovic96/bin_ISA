package com.bin448.backend.service;

import com.bin448.backend.converter.RoomConverter;
import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.entity.Room;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    public RoomServiceImpl(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
    }

    @Override
    public void addRoom(RoomDTO roomDTO) {
        hotelService.checkIfHotelExsists(roomDTO.getHotelId());
        roomRepository.save(RoomConverter.toEntity(roomDTO));
    }

    @Override
    public void removeRoom(Long id) {
        Room room = findRoomById(id);
        if (room.getReserved() == false) {
            room.setDeleted(true);
            roomRepository.save(room);
        }
    }

    @Override
    public void changeRoom(RoomDTO roomDTO, Long id) {
        Room room = findRoomById(id);
        hotelService.checkIfHotelExsists(roomDTO.getHotelId());
        if (room.getReserved() == false) {
            Hotel hotel = new Hotel();
            hotel.setHotel_id(roomDTO.getHotelId());
            room.setHotel(hotel);
            room.setPricePerNight(roomDTO.getPricePerNight());
            roomRepository.save(room);
        }
    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findByNumberAndDeleted(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
    }

}
