package com.bin448.backend.repository;

import com.bin448.backend.entity.RentACarBranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentACarBrenchOfficeRespository extends JpaRepository<RentACarBranchOffice,Long> {

    RentACarBranchOffice getById(Long ID);
    List<RentACarBranchOffice> findAllByCarService_CarServiceId(Long id);
    boolean deleteAllByCarService_CarServiceId(Long id);
  //  boolean modify(String address,Long idService,Long id);

}
