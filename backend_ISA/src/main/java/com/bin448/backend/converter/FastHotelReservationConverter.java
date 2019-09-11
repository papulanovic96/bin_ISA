package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.Discount;
import com.bin448.backend.entity.FastHotelReservation;

import java.util.Calendar;
import java.util.Date;

public class FastHotelReservationConverter extends AbstractConverter {
    public static FastHotelReservationDTO fromEntity(FastHotelReservation e) {
        FastHotelReservationDTO res = new FastHotelReservationDTO();
        res.setArrivalDate(e.getArrivalDate());
        res.setDestination(e.getDestination());
        res.setDiscountId(e.getDiscount().getId());
        res.setId(e.getId());
        res.setSumPrice(e.getSumPrice());
        res.setUserUsername(e.getUserUsername());
        Long numbOfNights = e.getEndDate().getTime() - e.getArrivalDate().getTime();
        res.setNumberOfNights(numbOfNights.intValue());
        return res;
    }

    public static FastHotelReservation toEntity(FastHotelReservationDTO e) {
        FastHotelReservation res = new FastHotelReservation();
        if (e.getArrivalDate() == null)
            e.setArrivalDate(new Date());
        res.setArrivalDate(e.getArrivalDate());
        res.setSumPrice(e.getSumPrice());
        res.setDestination(e.getDestination());
        res.setUserUsername(e.getUserUsername());
        res.setId(e.getId());
        Discount discount = new Discount();
        discount.setId(e.getDiscountId());
        res.setDiscount(discount);
        if (e.getNumberOfNights() != 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(e.getArrivalDate());
            c.add(Calendar.DATE, e.getNumberOfNights());
            res.setEndDate(c.getTime());
        } else {
            res.setEndDate(null);
        }
        return res;
    }
}
