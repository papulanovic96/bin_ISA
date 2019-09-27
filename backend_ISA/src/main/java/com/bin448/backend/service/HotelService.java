package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.AddressDTO;
import com.bin448.backend.entity.DTOentity.HotelDTO;

import java.util.HashMap;
import java.util.List;

public interface HotelService {

    String addHotel(HotelDTO hotelDTO);

    HotelDTO getHotel(Long hotelId);

    Boolean removeHotel(Long id);

    String changeHotel(HotelDTO hotelDTO, Long id);

    void checkIfHotelExists(Long id);

    List<HotelDTO> findAll();

    List<AddressDTO> findAllAddresses();

    HashMap<String, Double> getMenu(Long hotelId);

    String addMenuItem(String name, Double price, Long hotelId);

    String removeMenuItem(String name, Long hotelId);

    String getDescription(Long hotelId);

    List<HotelDTO> searchHotels(String name, String address, String arrival, String end);

    Double getMiddleGrade(Long hotelId);

}
