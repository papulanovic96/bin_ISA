package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelConverter extends AbstractConverter {

    public static HotelDTO fromEntity(Hotel e) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotel_id(e.getId());
        hotelDTO.setAddress(e.getAddress());
        hotelDTO.setDescription(e.getDescription());
        hotelDTO.setMenu(e.getMenu());
        hotelDTO.setName(e.getName());
        hotelDTO.setAvgGrade(e.getAvgGrade());
        return hotelDTO;
    }

    public static Hotel toEntity(HotelDTO e) {
        Hotel hotel = new Hotel();
        hotel.setId(e.getHotel_id());
        hotel.setAddress(e.getAddress());
        hotel.setDescription(e.getDescription());
        hotel.setMenu(e.getMenu());
        hotel.setName(e.getName());
        hotel.setAvgGrade(e.getAvgGrade());
        return hotel;
    }
}
