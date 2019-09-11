package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.DiscountDTO;
import com.bin448.backend.entity.Discount;
import com.bin448.backend.entity.Room;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DiscountConverter extends AbstractConverter {

    public static DiscountDTO fromEntity(Discount e) {
        DiscountDTO roomPrice = new DiscountDTO();
        roomPrice.setId(e.getId());
        roomPrice.setStartDate(e.getStartDate());
        Long duration = (e.getEndDate().getTime() - e.getStartDate().getTime())/(24*60*60*1000);
        System.out.println(duration+ " "+ duration.intValue()+ " AAAAAAAA");
        roomPrice.setDuration(duration.intValue());
        roomPrice.setNewPrice(e.getNewPrice());
        roomPrice.setRoomId(e.getRoom().getNumber());
        roomPrice.setDestination(e.getDestination());
        roomPrice.setAdditionalServices(e.getAdditionalServices());
        return roomPrice;
    }

    public static Discount toEntity(DiscountDTO e) {
        Discount roomPrice = new Discount();
        roomPrice.setId(e.getId());
        if (e.getStartDate() == null)
            e.setStartDate(new Date());
        roomPrice.setStartDate(e.getStartDate());
        Calendar c = Calendar.getInstance();
        c.setTime(e.getStartDate());
        c.add(Calendar.DATE, e.getDuration());
        roomPrice.setEndDate(c.getTime());
        roomPrice.setNewPrice(e.getNewPrice());
        roomPrice.setDestination(e.getDestination());
        Room room = new Room();
        room.setNumber(e.getRoomId());
        roomPrice.setRoom(room);
        roomPrice.setAdditionalServices(e.getAdditionalServices());
        return roomPrice;
    }
}
