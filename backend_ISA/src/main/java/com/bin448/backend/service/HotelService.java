package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.HotelDTO;

public interface HotelService {

    void addHotel(HotelDTO hotelDTO);

    void removeHotel(Long id);

    void changeHotel(HotelDTO hotelDTO, Long id);

    void checkIfHotelExsists(Long id);
}
