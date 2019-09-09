package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.entity.Room;
import com.bin448.backend.entity.RoomType;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter extends AbstractConverter {

    public static RoomDTO fromEntity(Room e) {
        RoomDTO room = new RoomDTO();
        room.setNumber(e.getNumber());
        room.setHotelId(e.getHotel().getId());
        room.setPricePerNight(e.getPricePerNight());
        room.setRoomType(e.getRoomType().getId());
        room.setAvgGrade(e.getAvgGrade());
        room.setFloor(e.getFloor());
        room.setReserved(e.isReserved());
        return room;
    }

    public static Room toEntity(RoomDTO e) {
        Room room = new Room();
        room.setNumber(e.getNumber());
        Hotel hotel = new Hotel();
        hotel.setId(e.getHotelId());
        room.setHotel(hotel);
        room.setPricePerNight(e.getPricePerNight());
        RoomType type = new RoomType();
        type.setId(e.getRoomType());
        room.setRoomType(type);
        room.setFloor(e.getFloor());
        room.setAvgGrade(e.getAvgGrade());
        room.setReserved(e.isReserved());
        room.setDeleted(false);
        return room;
    }

}
