package com.bin448.backend.service;

import com.bin448.backend.converter.HotelReservationConverter;
import com.bin448.backend.converter.RoomConverter;
import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.Room;
import com.bin448.backend.repository.HotelReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelReservationServiceImpl implements HotelReservationService {

    private final HotelReservationRepository hotelReservationRepository;
    private final RoomService roomService;

    public HotelReservationServiceImpl(HotelReservationRepository hotelReservationRepository, RoomService roomService) {
        this.hotelReservationRepository = hotelReservationRepository;
        this.roomService = roomService;
    }

    @Override
    public void addReservation(HotelReservationDTO reservation) {
        hotelReservationRepository.save(HotelReservationConverter.toEntity(reservation));
        Room roomFromReservation = roomService.findRoomById(reservation.getRoomId());
        if (roomFromReservation.isReserved() == false) {
            roomFromReservation.setReserved(true);
            System.out.println(roomFromReservation.getNumber() + " " + roomFromReservation.isReserved());
            roomService.changeRoomReserved(RoomConverter.fromEntity(roomFromReservation), roomFromReservation.getNumber());
        }

    }

}
