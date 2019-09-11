package com.bin448.backend.repository;

import com.bin448.backend.entity.RentACarBranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RentACarBrenchOfficeRespository extends JpaRepository<RentACarBranchOffice,Long> {

    RentACarBranchOffice getById(Long ID);
    RentACarBranchOffice findByAddress(String address);
    List<RentACarBranchOffice> findAllByCarService_CarServiceId(Long id);
    boolean deleteAllByCarService_CarServiceId(Long id);
    @Transactional
    @Modifying
    @Query(value = "update rentacar_branch_office set address = ?1 where id = ?2",nativeQuery = true)
    void modify(String address,Long id);


}
