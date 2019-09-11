package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.DiscountDTO;
import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.DTOentity.NewRoomPriceDTO;

import java.util.Date;
import java.util.List;

public interface NewRoomPriceService {

    void addNewPrice(NewRoomPriceDTO newRoomPriceDTO);

    Boolean checkIfPricesAlreadyExist(NewRoomPriceDTO newRoomPriceDTO);

    Boolean checkIfDiscountAlreadyExist(Date start, Date end, Long roomId);

    void addDiscount(DiscountDTO discountDTO);

    List<DiscountDTO> getValidDiscounts(FastHotelReservationDTO fastHotelReservationDTO);

}
