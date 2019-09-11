package com.bin448.backend.service;

import com.bin448.backend.converter.FastHotelReservationConverter;
import com.bin448.backend.converter.HotelReservationConverter;
import com.bin448.backend.converter.RoomConverter;
import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.Discount;
import com.bin448.backend.entity.FastHotelReservation;
import com.bin448.backend.entity.Room;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.repository.DiscountRepository;
import com.bin448.backend.repository.FastHotelReservationRepository;
import com.bin448.backend.repository.HotelReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HotelReservationServiceImpl implements HotelReservationService {

    private final HotelReservationRepository hotelReservationRepository;
    private final FastHotelReservationRepository fastHotelReservationRepository;
    private final RoomService roomService;
    private final DiscountRepository discountRepository;

    public HotelReservationServiceImpl(HotelReservationRepository hotelReservationRepository, FastHotelReservationRepository fastHotelReservationRepository, RoomService roomService, DiscountRepository discountRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
        this.fastHotelReservationRepository = fastHotelReservationRepository;
        this.roomService = roomService;
        this.discountRepository = discountRepository;
    }

    @Override
    public void addReservation(HotelReservationDTO reservation) {
        hotelReservationRepository.save(HotelReservationConverter.toEntity(reservation));
        Room roomFromReservation = roomService.findRoomById(reservation.getRoomId());
        if (roomFromReservation.isReserved() == false) {
            roomFromReservation.setReserved(true);
            roomService.changeRoomReserved(RoomConverter.fromEntity(roomFromReservation), roomFromReservation.getNumber());
        }
    }

    @Override
    public void addFastHotelReservation(FastHotelReservationDTO fastHotelReservationDTO) {
        fastHotelReservationRepository.save(FastHotelReservationConverter.toEntity(fastHotelReservationDTO));
        Discount discount = discountRepository.findById(fastHotelReservationDTO.getDiscountId())
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found", fastHotelReservationDTO.getDiscountId())));
        Room roomFromReservation = roomService.findRoomById(discount.getRoom().getNumber());
        if (roomFromReservation.isReserved() == false) {
            roomFromReservation.setReserved(true);
            roomService.changeRoomReserved(RoomConverter.fromEntity(roomFromReservation), roomFromReservation.getNumber());
        }
    }


}
