package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.entity.HotelReservation;
import com.bin448.backend.entity.Room;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class HotelReservationConverter extends AbstractConverter {

    public static HotelReservationDTO fromEntity(HotelReservation e) {
        HotelReservationDTO res = new HotelReservationDTO();
        res.setArrivalDate(e.getArrivalDate());
        res.setHotelId(e.getHotel().getId());
        res.setRoomId(e.getRoom().getNumber());
        res.setId(e.getId());
        res.setNumberOfGuests(e.getNumberOfGuests());
        res.setSumPrice(e.getSumPrice());
        res.setUserUsername(e.getUserUsername());
        res.setAdditionalServices(e.getAdditionalServices());
        Long numbOfNights = (e.getReturnDate().getTime() - e.getArrivalDate().getTime()) / (24 * 60 * 60 * 1000);
        res.setNumberOfNights(numbOfNights.intValue());
        return res;
    }

    public static HotelReservation toEntity(HotelReservationDTO e) {
        HotelReservation res = new HotelReservation();
        if (e.getArrivalDate() == null)
            e.setArrivalDate(new Date());
        res.setArrivalDate(e.getArrivalDate());
        res.setSumPrice(e.getSumPrice());
        Hotel hotel = new Hotel();
        hotel.setId(e.getHotelId());
        res.setHotel(hotel);
        Room room = new Room();
        room.setNumber(e.getRoomId());
        res.setRoom(room);
        res.setId(e.getId());
        res.setAdditionalServices(e.getAdditionalServices());
        res.setNumberOfGuests(e.getNumberOfGuests());
        res.setUserUsername(e.getUserUsername());
        Calendar c = Calendar.getInstance();
        c.setTime(e.getArrivalDate()); // Now use today date.
        c.add(Calendar.DATE, e.getNumberOfNights()); // Adding 5 days
        res.setReturnDate(c.getTime());
        return res;
    }
}
