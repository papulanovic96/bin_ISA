package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class HotelConverter extends AbstractConverter {

    public static HotelDTO fromEntity(Hotel e) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotel_id(e.getHotel_id());
        hotelDTO.setAddress(e.getAddress());
        hotelDTO.setDescription(e.getDescription());
        hotelDTO.setMenu(e.getMenu());
        hotelDTO.setName(e.getName());
        hotelDTO.setPool(e.getPool());
        hotelDTO.setRestaurant(e.getRestaurant());
        hotelDTO.setTV(e.getTV());
        hotelDTO.setWiFi(e.getWiFi());
        hotelDTO.setRoomService(e.getRoomService());
        Collection<Room> configuration = e.getConfiguration();
        List<Long> roomsID = new ArrayList<>();
        for (Room room : configuration) {
            roomsID.add(room.getNumber());
        }
        hotelDTO.setConfiguration(roomsID);
        return hotelDTO;
    }

    public static Hotel toEntity(HotelDTO e) {
        Hotel hotel = new Hotel();
        hotel.setHotel_id(e.getHotel_id());
        hotel.setAddress(e.getAddress());
        hotel.setDescription(e.getDescription());
        hotel.setMenu(e.getMenu());
        hotel.setName(e.getName());
        hotel.setPool(e.getPool());
        hotel.setRestaurant(e.getRestaurant());
        hotel.setTV(e.getTV());
        hotel.setWiFi(e.getWiFi());
        hotel.setRoomService(e.getRoomService());

        Collection<Room> configuration = null;
        List<Long> roomsID = e.getConfiguration();
        Room newRoom = new Room();
        if (roomsID != null) {
            for (Long key : roomsID) {
                newRoom.setNumber(key);
                configuration.add(newRoom);
            }
        }
        hotel.setConfiguration(configuration);
        hotel.setAvgGrade(0d);
        return hotel;
    }
}
