package com.bin448.backend.repository;

import com.bin448.backend.entity.CarDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDiscountRepository  extends JpaRepository<CarDiscount,Long> {
}
