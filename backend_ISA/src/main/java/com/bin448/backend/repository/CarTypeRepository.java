package com.bin448.backend.repository;

import com.bin448.backend.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CarTypeRepository extends JpaRepository<CarType,Long> {

    @Transactional
    @Modifying
    @Query(value = "update car_type set seats = ?1,car_id = ?2 where id = ?3",nativeQuery = true)
    void update(Integer seats,Long cid,Long id);
}
