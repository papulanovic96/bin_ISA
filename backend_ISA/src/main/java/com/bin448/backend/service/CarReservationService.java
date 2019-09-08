package com.bin448.backend.service;

import com.bin448.backend.entity.CarReservation;
import com.bin448.backend.entity.DTOentity.CarReservationDTO;

public interface CarReservationService {
    CarReservation findByUserId(Long id);
    CarReservation findBycarId(Long id);
    void addReservation(CarReservationDTO cr);
    boolean IsDeleteReservationPosible(Long id, Long userId);
    boolean isUserAbleToRate(Long id, Long userId);
    String removeReservation(Long id,Long userId);
    boolean IsUserReservedCar(Long id,Long userId);

}
