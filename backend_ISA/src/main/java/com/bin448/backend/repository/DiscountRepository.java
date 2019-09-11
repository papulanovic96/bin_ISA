package com.bin448.backend.repository;

import com.bin448.backend.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    List<Discount> findByRoom_Number(Long roomId);

    List<Discount> findByDestination(String destination);
}
