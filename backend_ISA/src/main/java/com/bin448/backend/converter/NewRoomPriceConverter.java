package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.NewRoomPriceDTO;
import com.bin448.backend.entity.NewRoomPrice;
import com.bin448.backend.entity.Room;

import java.util.Calendar;
import java.util.Date;

public class NewRoomPriceConverter extends AbstractConverter {

    public static NewRoomPriceDTO fromEntity(NewRoomPrice e) {
        NewRoomPriceDTO roomPrice = new NewRoomPriceDTO();
        roomPrice.setId(e.getId());
        roomPrice.setStartDate(e.getStartDate());
        Long duration = e.getEndDate().getTime() - e.getStartDate().getTime();
        roomPrice.setDuration(duration.intValue());
        roomPrice.setNewPrice(e.getNewPrice());
        roomPrice.setRoomId(e.getRoom().getNumber());
        return roomPrice;
    }

    public static NewRoomPrice toEntity(NewRoomPriceDTO e) {
        NewRoomPrice roomPrice = new NewRoomPrice();
        roomPrice.setId(e.getId());
        if (e.getStartDate() == null)
            e.setStartDate(new Date());
        roomPrice.setStartDate(e.getStartDate());
        Calendar c = Calendar.getInstance();
        c.setTime(e.getStartDate());
        c.add(Calendar.DATE, e.getDuration());
        roomPrice.setEndDate(c.getTime());
        roomPrice.setNewPrice(e.getNewPrice());
        Room room = new Room();
        room.setNumber(e.getRoomId());
        roomPrice.setRoom(room);
        return roomPrice;
    }
}
