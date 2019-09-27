package com.bin448.backend.service;

import com.bin448.backend.converter.HotelReservationConverter;
import com.bin448.backend.converter.RoomConverter;
import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.HotelReservation;
import com.bin448.backend.entity.Room;
import com.bin448.backend.entity.RoomRate;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.repository.HotelReservationRepository;
import com.bin448.backend.repository.RoomRateRepository;
import com.bin448.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;
    private final HotelReservationRepository hotelReservationRepository;
    private final NewRoomPriceService newRoomPriceService;
    private final RoomRateRepository roomRateRepository;

    public RoomServiceImpl(RoomRateRepository roomRateRepository, RoomRepository roomRepository, NewRoomPriceService newRoomPriceService, HotelService hotelService, HotelReservationRepository hotelReservationRepository) {
        this.roomRepository = roomRepository;
        this.hotelService = hotelService;
        this.hotelReservationRepository = hotelReservationRepository;
        this.newRoomPriceService = newRoomPriceService;
        this.roomRateRepository = roomRateRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> findAll() {
        return changeListOfRoomToRoomDTO(roomRepository.findByDeleted(false));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String addRoom(RoomDTO roomDTO) {
        hotelService.checkIfHotelExists(roomDTO.getHotelId());
        roomDTO.setNewPrice(0d);
        roomDTO.setAvgGrade(0d);
        roomDTO.setReserved(false);
        roomRepository.save(RoomConverter.toEntity(roomDTO));
        return "SUCCESS";
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String removeRoom(Long id) {
        Room room = findRoomById(id);
        if (!existReservationWithRoomId(id)) {
            room.setDeleted(true);
            roomRepository.save(room);
        }
        return "SUCCESS";
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String changeRoom(RoomDTO roomDTO, Long id) {
        roomDTO.setNumber(id);
        Room room = findRoomById(id);
        hotelService.checkIfHotelExists(roomDTO.getHotelId());
        if (!existReservationWithRoomId(id)) {
            roomRepository.save(RoomConverter.toEntity(roomDTO));
        }
        return "SUCCESS";
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void changeRoomReserved(RoomDTO roomDTO, Long id) {
        roomDTO.setNumber(id);
        roomRepository.save(RoomConverter.toEntity(roomDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Room findRoomById(Long id) {
        return roomRepository.findByNumberAndDeleted(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Room with id %s not found", id)));
    }

    @Override
    public RoomDTO findRoomDTOById(Long id) {
        return RoomConverter.fromEntity(findRoomById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDTO> findRoomsByHotelId(Long hotelId) {
        return changeListOfRoomToRoomDTO(roomRepository.findByHotel_IdAndDeleted(hotelId, false));
    }

    @Override
    @Transactional(readOnly = true)
    public List<List<RoomDTO>> findRoomsFromReservation(List<RoomDTO> rooms, HotelReservationDTO hotelReservationDTO) {
        HotelReservation hotelReservation = HotelReservationConverter.toEntity(hotelReservationDTO);
        List<List<RoomDTO>> allRooms = new ArrayList<>();
        for (RoomDTO room : rooms) {
            List<RoomDTO> appropriateRoom = new ArrayList<RoomDTO>();
            List<RoomDTO> possibleRooms = changeListOfRoomToRoomDTO(roomRepository.findByHotel_IdAndRoomType_IdAndDeleted(room.getHotelId(), room.getRoomType(), false));
            for (RoomDTO possibleRoom : possibleRooms) {
                if (possibleRoom.getNewPrice() == 0) {
                    if (room.getPricePerNight() >= possibleRoom.getPricePerNight() || room.getPricePerNight() == 0) {
                        if (checkIfThereIsAvailableRoom(possibleRoom, hotelReservationDTO)) {
                            if (newRoomPriceService.checkIfDiscountAlreadyExist(hotelReservation.getArrivalDate(), hotelReservation.getReturnDate(), possibleRoom.getNumber())) {
                                appropriateRoom.add(possibleRoom);
                            }
                        }
                    }
                } else {
                    if (room.getPricePerNight() >= possibleRoom.getNewPrice() || room.getPricePerNight() == 0) {
                        if (checkIfThereIsAvailableRoom(possibleRoom, hotelReservationDTO)) {
                            if (newRoomPriceService.checkIfDiscountAlreadyExist(hotelReservation.getArrivalDate(), hotelReservation.getReturnDate(), possibleRoom.getNumber())) {
                                appropriateRoom.add(possibleRoom);
                            }
                        }
                    }
                }
            }
            allRooms.add(appropriateRoom);
        }
        return allRooms;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkIfThereIsAvailableRoom(RoomDTO room, HotelReservationDTO hotelReservationDTO) {
        HotelReservation hotelReservation = HotelReservationConverter.toEntity(hotelReservationDTO);

        List<HotelReservation> hotelReservations = hotelReservationRepository.getByRoom_Number(room.getNumber());
        boolean notReserved = true;

        for (HotelReservation r : hotelReservations) {
            if ((hotelReservation.getArrivalDate().compareTo(r.getReturnDate()) > 0 && hotelReservation.getReturnDate().compareTo(r.getReturnDate()) > 0 && hotelReservation.getArrivalDate().compareTo(r.getArrivalDate()) > 0 && hotelReservation.getReturnDate().compareTo(r.getArrivalDate()) > 0)) { //date1 je posle rezervisanog odlaska

            } else if (hotelReservation.getArrivalDate().compareTo(r.getReturnDate()) < 0 && hotelReservation.getReturnDate().compareTo(r.getReturnDate()) < 0 && hotelReservation.getArrivalDate().compareTo(r.getArrivalDate()) < 0 && hotelReservation.getReturnDate().compareTo(r.getArrivalDate()) < 0) {

            } else {
                notReserved = false;
            }
        }

        return notReserved;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existReservationWithRoomId(Long roomId) {
        return hotelReservationRepository.getByRoom_Number(roomId).size() != 0;
    }

    public List<RoomDTO> changeListOfRoomToRoomDTO(List<Room> rooms) {
        return rooms.stream()
                .map(room -> RoomConverter.fromEntity(room))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Double getMiddleGrade(Long roomId) {
        List<RoomRate> rates = roomRateRepository.findByRoom_Number(roomId);
        if (rates.size() == 0)
            return 0d;
        Double sum = 0d;
        int counter = 0;
        for (RoomRate roomRate : rates) {
            sum += roomRate.getRate();
        }
        Double middle = sum / rates.size();
        return middle;
    }

}
