package com.bin448.backend.controller;

import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
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
}
