package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.RoomType;
import com.bin448.backend.service.HotelReservationService;
import com.bin448.backend.service.RoomService;
import com.bin448.backend.service.RoomTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private static HotelReservationDTO hotelReservationDTO = new HotelReservationDTO();
    private final RoomService roomService;
    private final RoomTypeService roomTypeService;
    private final HotelReservationService hotelReservationService;

    public RoomController(HotelReservationService hotelReservationService, RoomService roomService, RoomTypeService roomTypeService) {
        this.roomService = roomService;
        this.roomTypeService = roomTypeService;
        this.hotelReservationService = hotelReservationService;
    }

    @PostMapping("/addRoom")
    public ResponseEntity<String> addRoom(@RequestBody @Valid RoomDTO roomDTO) {
        roomService.addRoom(roomDTO);
        return ResponseEntity.ok("Successfully added room");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeRoom(@PathVariable Long id) {
        roomService.removeRoom(id);
        return ResponseEntity.ok(String.format("Room with number %s is removed", id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> changeRoom(@PathVariable Long id, @RequestBody @Valid RoomDTO roomDTO) {
        roomService.changeRoom(roomDTO, id);
        return ResponseEntity.ok(String.format("Room with number %s has been successfully changed", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findRoomDTOById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findRoomDTOById(id));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomDTO>> findRoomsByHotelId(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.findRoomsByHotelId(hotelId));
    }

    @GetMapping("/checkAvailable/{listOfTypeIds}/{listOfPrices}")
    public ResponseEntity<List<List<RoomDTO>>> findAvailableRooms(@PathVariable List<Long> listOfTypeIds, @PathVariable List<Double> listOfPrices) {
        List<RoomDTO> rooms = new ArrayList<>();
        for (int i = 0; i < listOfTypeIds.size(); i++) {
            RoomDTO room = new RoomDTO();
            room.setHotelId(RoomController.hotelReservationDTO.getHotelId());
            room.setRoomType(listOfTypeIds.get(i));
            room.setPricePerNight(listOfPrices.get(i));
            rooms.add(room);
        }
        List<List<RoomDTO>> lista = roomService.findRoomsFromReservation(rooms, RoomController.hotelReservationDTO);
        return ResponseEntity.ok(roomService.findRoomsFromReservation(rooms, RoomController.hotelReservationDTO));
    }

    @PostMapping("/setReservation")
    public ResponseEntity<String> setReservation(@RequestBody HotelReservationDTO hotelReservationDTO) {
        RoomController.hotelReservationDTO = hotelReservationDTO;
        return ResponseEntity.ok("HotelReservation is set");
    }

    @GetMapping("/reserved/{roomId}")
    public ResponseEntity<Boolean> reservedRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(roomService.existReservationWithRoomId(roomId));
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> findAllRooms() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/roomTypes")
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        return ResponseEntity.ok(roomTypeService.findAll());
    }

    @GetMapping("/rate/{id},{username},{grade}")
    public boolean rate(@PathVariable Long id, @PathVariable String username, @PathVariable Double grade) {
        return hotelReservationService.rateRoom(id, username, grade);
    }

    @GetMapping("/middleGrade/{roomId}")
    public ResponseEntity<Double> getMiddleGrade(@PathVariable Long roomId) {
        return ResponseEntity.ok(roomService.getMiddleGrade(roomId));
    }
}
