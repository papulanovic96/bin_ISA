package com.bin448.backend.repository;

import com.bin448.backend.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation,Long> {

    CarReservation findByUserId(Long userId);
    CarReservation findByCarId(Long id);
    @Modifying
    @Transactional
    void deleteByCarResId(Long id);



    @Modifying
    @Transactional
    void deleteAllByCarIdAndUserId(Long id,Long id2);
    @Query(value = "select * from car_reservation  where car_id = ?1 and user_id = ?2 and end_date<=now()",nativeQuery = true)
    List<CarReservation> findAllByCarIdAndUserId(Long id, Long idUser);
    CarReservation findByCarIdAndUserId(Long id,Long userid);

    @Query(value = "SELECT * FROM car_reservation where car_id =?1 and user_id = ?2 and start_date > now() + interval 2 day",nativeQuery = true)
    CarReservation checkCanceling(Long carID,Long userID);

    @Query(value = "SELECT cr.car_res_id,cr.car_id,cr.end_date,cr.start_date,cr.user_id FROM car_reservation cr right outer join cars c on c.car_id = cr.car_id right outer join car_service cs on cs.car_service_id = c.id_service where cr.end_date<now() and cs.car_service_id = ?1 and cr.user_id = ?2",nativeQuery = true)
    List<CarReservation> checkIfEverReservedAnyCar(Long serviceId,Long userId);

    

}

