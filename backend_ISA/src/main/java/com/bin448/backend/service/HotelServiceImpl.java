package com.bin448.backend.service;

import com.bin448.backend.converter.HotelConverter;
import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.repository.HotelRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void addHotel(HotelDTO hotelDTO) {
        hotelRepository.save(HotelConverter.toEntity(hotelDTO));
    }

    @Override
    public void removeHotel(Long id) {

    }

    @Override
    public void changeHotel(HotelDTO hotelDTO, Long id) {

    }
}
