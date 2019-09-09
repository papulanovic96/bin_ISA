package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.NewRoomPriceDTO;

public interface NewRoomPriceService {

    void addNewPrice(NewRoomPriceDTO newRoomPriceDTO);

    Boolean checkIfPricesAlreadyExist(NewRoomPriceDTO newRoomPriceDTO);

}
