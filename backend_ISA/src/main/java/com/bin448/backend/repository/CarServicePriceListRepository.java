package com.bin448.backend.repository;

import com.bin448.backend.entity.CarServicePriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface CarServicePriceListRepository extends JpaRepository<CarServicePriceList,Long> {

    List<CarServicePriceList> findAllByCarS_CarServiceName(String name);
    List<CarServicePriceList> findAllByCarS_CarServiceId(Long id);
    CarServicePriceList findByCarS_CarServiceIdAndCar_CarId(Long sId,Long cId);
/*
    @Transactional
    @Modifying
    void deleteByName(String name);
*/
    @Transactional
    @Modifying
    void deleteByIdCarServicePriceList(Long id);

    void deleteAllByCarS_CarServiceId(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update carpricelist  price = ?1 where id_car_service_price_list = ?2",nativeQuery = true)
    void modfyCarPriceListItem(Double price, Long id);


    CarServicePriceList findByCar_CarId(Long id);

    CarServicePriceList findByIdCarServicePriceList(Long id);

    @Query(value = "select sum(price) from isa.carpricelist cp  where cp.car_id in :input",nativeQuery = true)
    Integer getTotalPrice(@Param("input")Integer[] array);

    @Query(value = "select price from carpricelist where car_id = ?1",nativeQuery = true)
    Integer getPrice(Long ID);
}
