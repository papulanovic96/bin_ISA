package com.bin448.backend.repository;

import com.bin448.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    Car getCarByRegID(String regId);

    @Modifying
    @Transactional
    @Query(value = "update cars set deleted = 1 where id_service = ?1",nativeQuery = true)

    void deleteAllByCarService_CarServiceId(Long id);
 //   Car findByCarService_CarServiceName(String name);
    @Query(value = "SELECT * FROM cars c right outer join car_service cs on c.id_service = cs.car_service_id where c.deleted = 0 and cs.car_service_name = ?1",nativeQuery = true)
    List<Car> findAllByCarService_CarServiceName(String name);
    @Query(value = "SELECT * FROM cars c where c.deleted = 0 and c.car_service_id = ?1",nativeQuery = true)
    List<Car> findAllByCarService_CarServiceId(Long id);
    @Query(value = "select * from cars where deleted = 0",nativeQuery = true)
    List<Car> findAll();
    Car getCarByCarId(Long id);

    @Modifying
    @Transactional
    @Query(value = "Update cars set reserved = ?1 where car_id = ?2", nativeQuery = true)
    void modifyReserved(boolean reserved, Long id);

    @Modifying
    @Transactional
    @Query(value = "Update cars set deleted = ?1 where regid = ?2", nativeQuery = true)
    void deleteSelectedCar(boolean deleted, String regID);

    @Modifying
    @Transactional
    @Query(value = "Update cars set model = ?1, typec= ?2, year = ?3, convertible = ?4, regid = ?5, id_service = ?6 where car_id = ?7", nativeQuery = true)
    void modifyCar(String model, String type, Integer year, Boolean convertible, String regID,Long serviceID,Long carID);

    @Query(value = "SELECT * FROM cars c left outer join car_reservation cr on c.car_id = cr.car_id where cr.user_id = ?1", nativeQuery = true)
    List<Car> getReservedCars(Long userID);


    @Modifying
    @Transactional
    @Query(value = "Update cars set avg_grade = ?1 where car_id = ?2", nativeQuery = true)
    void rateSelectedCar(Double grade, Long regID);
    @Query(value = "select * from cars c right outer join car_type ct on ct.car_id = c.car_id where ct.seats  like  %?5% and ct.name like ?2 and model like %?1%  and year > ?3 and year < ?4 and deleted = 0",nativeQuery = true)
    List<Car> search(String model,String typec,Integer from,Integer to,Integer nos);
    @Query(value = "select * from cars where model like %?1% and typec like %?2% and year > ?3 and year < ?4 and deleted = 0",nativeQuery = true)
    List<Car> searchWithoutSeats(String model,String typec,Integer from,Integer to);

    @Query(value = "SELECT distinct c.car_id FROM cars c right outer join carpricelist cpl on c.car_id = cpl.car_id right outer join car_reservation cr on cr.car_id = c.car_id right outer join car_type ct on ct.car_id = c.car_id where ct.name like %?1% and c.deleted = 0 and cpl.price > ?2 and cpl.price < ?3 and ((cr.end_date < ?4 and cr.start_date < ?4) or (cr.end_date > ?5 and cr.start_date > ?5))",nativeQuery = true)
    List<Long> getAvailableCars(String type, Integer priceFrom, Integer priceTo, String start, String end);
}
