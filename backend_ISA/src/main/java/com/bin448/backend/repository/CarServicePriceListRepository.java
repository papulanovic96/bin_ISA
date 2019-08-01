package com.bin448.backend.repository;

import com.bin448.backend.entity.CarServicePriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface CarServicePriceListRepository extends JpaRepository<CarServicePriceList,Long> {

    List<CarServicePriceList> findAllByCarS_CarServiceName(String name);
    @Transactional
    @Modifying
    void deleteByName(String name);

    @Transactional
    @Modifying
    void deleteByIdCarServicePriceList(Long id);

    @Transactional
    @Modifying
    @Query(value = "Update carpricelist set name = ?1 , price = ?2 where id_car_service_price_list = ?3",nativeQuery = true)
    void modfyCarPriceListItem(String name, Double price, Long id);
    CarServicePriceList findByIdCarServicePriceList(Long id);

}
