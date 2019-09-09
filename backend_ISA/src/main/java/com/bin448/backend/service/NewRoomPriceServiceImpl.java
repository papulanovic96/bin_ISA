package com.bin448.backend.service;

import com.bin448.backend.converter.NewRoomPriceConverter;
import com.bin448.backend.entity.DTOentity.NewRoomPriceDTO;
import com.bin448.backend.entity.NewRoomPrice;
import com.bin448.backend.repository.NewRoomPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewRoomPriceServiceImpl implements NewRoomPriceService {

    private final NewRoomPriceRepository newRoomPriceRepository;

    public NewRoomPriceServiceImpl(NewRoomPriceRepository newRoomPriceRepository) {
        this.newRoomPriceRepository = newRoomPriceRepository;
    }

    @Override
    public Boolean checkIfPricesAlreadyExist(NewRoomPriceDTO newRoomPriceDTO) {
        NewRoomPrice newPrice = NewRoomPriceConverter.toEntity(newRoomPriceDTO);

        List<NewRoomPrice> newPrices = newRoomPriceRepository.findByRoom_Number(newRoomPriceDTO.getRoomId());
        boolean notFound = true;

        for (NewRoomPrice r : newPrices) {
            if ((newPrice.getStartDate().compareTo(r.getEndDate()) > 0 && newPrice.getEndDate().compareTo(r.getEndDate()) > 0 && newPrice.getStartDate().compareTo(r.getStartDate()) > 0 && newPrice.getEndDate().compareTo(r.getStartDate()) > 0)) { //date1 je posle rezervisanog odlaska

            } else if (newPrice.getStartDate().compareTo(r.getEndDate()) < 0 && newPrice.getEndDate().compareTo(r.getEndDate()) < 0 && newPrice.getStartDate().compareTo(r.getStartDate()) < 0 && newPrice.getEndDate().compareTo(r.getStartDate()) < 0) {

            } else {
                notFound = false;
            }
        }

        return notFound;
    }


    @Override
    public void addNewPrice(NewRoomPriceDTO newRoomPriceDTO) {
        newRoomPriceRepository.save(NewRoomPriceConverter.toEntity(newRoomPriceDTO));
    }

    //dodaj cenu ako ne postoji vec cena u tom datumu
    //obrisi cenu
    //vidi jel postoji cena u tom datumu
}
