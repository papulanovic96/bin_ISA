package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomConverter extends AbstractConverter {

    public static RoomDTO fromEntity(Room e) {
        RoomDTO room = new RoomDTO();
        room.setNumber(e.getNumber());
        room.setHotelId(e.getHotel().getHotel_id());
        room.setPricePerNight(e.getPricePerNight());
        return room;
    }

    public static Room toEntity(RoomDTO e) {
        Room room = new Room();
        room.setNumber(e.getNumber());
        Hotel hotel = new Hotel();
        hotel.setHotel_id(e.getHotelId());
        room.setHotel(hotel);
        room.setPricePerNight(e.getPricePerNight());
        room.setAvgGrade(0d);
        room.setDeleted(false);
        room.setReserved(false);
        return room;
    }

}
