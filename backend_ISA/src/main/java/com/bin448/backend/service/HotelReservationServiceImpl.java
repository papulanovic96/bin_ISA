package com.bin448.backend.service;

import com.bin448.backend.converter.FastHotelReservationConverter;
import com.bin448.backend.converter.HotelReservationConverter;
import com.bin448.backend.converter.RoomConverter;
import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.repository.*;
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
    private final HotelRateRepository hotelRateRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final RoomRateRepository roomRateRepository;
    private final RoomRepository roomRepository;

    public HotelReservationServiceImpl(RoomRepository roomRepository, RoomRateRepository roomRateRepository, HotelRepository hotelRepository, UserRepository userRepository, HotelRateRepository hotelRateRepository,  HotelReservationRepository hotelReservationRepository, FastHotelReservationRepository fastHotelReservationRepository, RoomService roomService, DiscountRepository discountRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
        this.fastHotelReservationRepository = fastHotelReservationRepository;
        this.roomService = roomService;
        this.discountRepository = discountRepository;
        this.hotelRateRepository = hotelRateRepository;
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
        this.roomRateRepository = roomRateRepository;
        this.roomRepository = roomRepository;
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

    @Override
    public Boolean rateHotel(Long id, String username,Double rate) {
        HotelRate hotelRate = new HotelRate();
        hotelRate.setHotel(hotelRepository.findById(id).get());
        hotelRate.setUser(userRepository.getUserByUsername(username));
        hotelRate.setId(null);
        hotelRate.setRate(rate);
        List<HotelReservation> reservations = hotelReservationRepository.getAllReservations(id,username);
        List<HotelRate> rates = hotelRateRepository.findAllByUser_IdAndHotel_Id(userRepository.findByUsername(username).getId(),id);
        if(reservations.size() != 0 && rates.size() == 0)
        {
            hotelRateRepository.save(hotelRate);
            return true;
        }
    /*   else if(!hotelReservationRepository.existsByUserUsernameAndHotel_Id(username,id) && rates.size() == 0){
            hotelRateRepository.save(hotelRate);
            return true;
        }*/
        return false;
    }

    @Override
    public Boolean rateRoom(Long id, String username, Double rate) {
        RoomRate roomRate = new RoomRate();
        roomRate.setId(null);
        roomRate.setRate(rate);
        roomRate.setRoom(roomRepository.findByNumberAndDeleted(id,false).get());
        roomRate.setUser(userRepository.findByUsername(username));
        List<HotelReservation> reservations = hotelReservationRepository.getAllReservationsRooms(id,username);
        List<RoomRate> rates = roomRateRepository.findAllByUser_IdAndRoom_Number(userRepository.findByUsername(username).getId(),id);
        if(reservations.size()!=0 && rates.size() == 0){
            roomRateRepository.save(roomRate);
            return true;
        }
      /*  else if(!hotelReservationRepository.existsByUserUsernameAndRoom_Number(username,id)  && rates.size() == 0){
            roomRateRepository.save(roomRate);
            return true;
        }*/
            return false;
    }


}
