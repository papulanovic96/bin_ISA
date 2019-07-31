package com.bin448.backend.repository;

import com.bin448.backend.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation,Long> {

    CarReservation findByUsername(String username);
    CarReservation findByCarREG(String regID);
    @Modifying
    @Transactional
    void deleteByCarResId(Long id);
    CarReservation findByCarREGAndUsername(String carREG,String username);
    

}

