package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.entity.HotelReservation;
import com.bin448.backend.entity.Room;
import com.bin448.backend.entity.User;

import java.util.Calendar;

public class HotelReservationConverter extends AbstractConverter {

    public static HotelReservationDTO fromEntity(HotelReservation e) {
        HotelReservationDTO res = new HotelReservationDTO();
        res.setArrivalDate(e.getArrivalDate());
        res.setHotelId(e.getHotel().getId());
        res.setRoomId(e.getRoom().getNumber());
        res.setId(e.getId());
        res.setNumberOfGuests(e.getNumberOfGuests());
        res.setUserId(e.getUser().getId());
        res.setAdditionalServices(e.getAdditionalServices());
        Long numbOfNights = e.getReturnDate().getTime() - e.getArrivalDate().getTime();
        res.setNumberOfNights(numbOfNights.intValue());
        return res;
    }

    public static HotelReservation toEntity(HotelReservationDTO e) {
        HotelReservation res = new HotelReservation();
        res.setArrivalDate(e.getArrivalDate());
        Hotel hotel = new Hotel();
        hotel.setId(e.getHotelId());
        res.setHotel(hotel);
        Room room = new Room();
        room.setNumber(e.getRoomId());
        res.setRoom(room);
        res.setId(e.getId());
        res.setAdditionalServices(e.getAdditionalServices());
        res.setNumberOfGuests(e.getNumberOfGuests());
        User user = new User();
        user.setId(e.getUserId());
        res.setUser(user);
        Calendar c = Calendar.getInstance();
        c.setTime(e.getArrivalDate()); // Now use today date.
        c.add(Calendar.DATE, e.getNumberOfNights()); // Adding 5 days
        res.setReturnDate(c.getTime());
        System.out.println(res.getReturnDate());
        return res;
    }
}
