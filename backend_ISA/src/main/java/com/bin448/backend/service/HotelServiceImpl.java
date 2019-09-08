package com.bin448.backend.service;

import com.bin448.backend.converter.HotelConverter;
import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.exception.ForeignKeyConstraintException;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.repository.HotelRepository;
import com.bin448.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public HotelServiceImpl(HotelRepository hotelRepository,RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository=roomRepository;
    }

    @Override
    public HotelDTO getHotel(Long hotelId) {
        return HotelConverter.fromEntity(findHotel(hotelId));
    }

    @Override
    public List<HotelDTO> findAll() {
        return hotelRepository.findByDeleted(false).stream()
                .map(hotel -> HotelConverter.fromEntity(hotel))
                .collect(Collectors.toList());
    }

    @Override
    public void addHotel(HotelDTO hotelDTO) {
        hotelDTO.setAvgGrade(0d);
        Hotel newHotel = HotelConverter.toEntity(hotelDTO);
        newHotel.setDeleted(false);
        hotelRepository.save(newHotel);
    }

    @Override
    public void addMenuItem(String name, Double price, Long hotelId) {
        Hotel hotel = findHotel(hotelId);
        if (hotel.getMenu() == null) {
            HashMap<String, Double> menu = new HashMap<>();
            menu.put(name, price);
            hotel.setMenu(menu);
        } else {
            hotel.getMenu().put(name, price);
        }
        hotelRepository.save(hotel);
    }

    @Override
    public void removeMenuItem(String name, Long hotelId) {
        Hotel hotel = findHotel(hotelId);
        if (hotel.getMenu() != null) {
            HashMap<String, Double> newMenu = hotel.getMenu();
            for (Iterator<String> it = newMenu.keySet().iterator(); it.hasNext(); ) {
                String itemName = it.next();
                if (itemName.equals(name)) {
                    it.remove();
                    break;
                }
            }
            hotel.setMenu(newMenu);
            hotelRepository.save(hotel);
        }
    }

    @Override
    public HashMap<String, Double> getMenu(Long hotelId) {
        return findHotel(hotelId).getMenu();
    }

    @Override
    public String getDescription(Long hotelId) {
        return findHotel(hotelId).getDescription();
    }

    @Override
    public Boolean removeHotel(Long id) {
        if(roomRepository.findByHotel_IdAndDeleted(id,false).size()==0){
            Hotel hotel = findHotel(id);
            hotel.setDeleted(true);
            hotelRepository.save(hotel);
            return true;
        }
        return false;
    }

    @Override
    public void changeHotel(HotelDTO hotelDTO, Long id) {
        hotelDTO.setHotel_id(id);
        Hotel hotel = findHotel(id);
        hotelRepository.save(HotelConverter.toEntity(hotelDTO));
    }

    @Override
    public void checkIfHotelExists(Long id) {
        hotelRepository.findByIdAndDeleted(id, false)
                .orElseThrow(() -> new ForeignKeyConstraintException(String.format("Hotel with id %s not found", id)));
    }

    private Hotel findHotel(Long hotelId) {
        return hotelRepository.findByIdAndDeleted(hotelId, false)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", hotelId)));
    }
}
