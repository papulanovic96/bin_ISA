package com.bin448.backend.service;

import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;

public interface CarReservationService {
    CarReservation findByUsername(String username);
    CarReservation findByRegId(String regID);
    void addReservation(CarReservationDTO cr);
    String deleteReservation(String regID, String username);

}
