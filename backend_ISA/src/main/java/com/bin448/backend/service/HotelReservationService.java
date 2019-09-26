package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.DTOentity.HotelReservationDTO;

public interface HotelReservationService {

    void addReservation(HotelReservationDTO reservation);

    void addFastHotelReservation(FastHotelReservationDTO fastHotelReservationDTO);

    Boolean rateHotel(Long id, String username, Double rate);

    Boolean rateRoom(Long id,String username,Double rate);

}
