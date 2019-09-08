package com.bin448.backend.service;

import com.bin448.backend.converter.CarServiceConverter;
import com.bin448.backend.converter.CarServicePriceListConverter;
import com.bin448.backend.converter.CarServiceRateConverter;
import com.bin448.backend.entity.*;
import com.bin448.backend.entity.CarService;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;

import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.repository.*;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceServiceImpl implements CarServiceService {

    private CarServiceRepository csr;

    private CarRepository cr;
    private CarServicePriceListRepository csPrice;
    private CarServiceRateRepository csrr;
    private CarReservationRepository crr;
    public CarServiceServiceImpl(CarReservationRepository crr,CarRepository cr,CarServiceRateRepository csrr, CarServicePriceListRepository csPrice, CarServiceRepository csr){
        this.csrr = csrr;
        this.crr = crr;
        this.csPrice = csPrice;
        this.csr=csr;
        this.cr = cr;
    }


    public CarServiceServiceImpl(CarServiceRepository csr) {
        this.csr = csr;
    }


    @Override
    public String addCarService(CarServiceDTO cs) {
        String ret = "ADDING FAILED!";
        try {
            CarService cService = CarServiceConverter.toEntity(cs);
            cService.setDeleted(false);
            csr.save(cService);
            ret = "CAR SERVICE HAS BEEN SUCCESSFULLY ADDED";
        }
        catch (Exception e){
          e.printStackTrace();
        }
        finally {
            return ret;
        }
        }

    @Override
    public String logicRemoveCarService(Long id) {
        String ret = "REMOVING FAILED!";
        try {
            csr.logicalDeleting(true,id);
            cr.deleteAllByCarService_CarServiceId(id);
            ret = "SUCCESS";
        }catch (Exception e){
           e.printStackTrace();
        }
        finally {
            return ret;
        }
        }

    @Override
    public String removeCarService(Long id) {
        String ret = "ERROR!";
        try {
            csr.deleteCarServiceByCarServiceId(id);
            ret = "SUCCESS!";
        }
        catch (Exception e){
                e.printStackTrace();
        }finally {
            return ret;
        }
        }

    @Override
    public CarServicePriceListDTO getItem(Long id) {
        CarServicePriceListDTO cs = new CarServicePriceListDTO();
        try{
            cs = CarServicePriceListConverter.fromEntity(csPrice.findByIdCarServicePriceList(id));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return cs;
        }
    }

    @Override
    public String addItem(CarServicePriceList cs) {
        String ret = "ERROR!";
        try {
            csPrice.save(cs);
            ret = "SUCCESS";
        }catch (Exception e){
                e.printStackTrace();
        }finally {
            return ret;
        }

    }

    @Override
    public String deleteItem(Long id) {
    String ret = "ERROR!";
    try {
        csPrice.deleteByIdCarServicePriceList(id);
        ret = "SUCCESS!";
    }catch (Exception e){
             e.printStackTrace();
    }finally {
        return ret;
    }

    }

    @Override
    public CarServiceDTO findCarService(String name) {
        if(csr.getCarServiceByCarServiceName(name)!=null || !csr.getCarServiceByCarServiceName(name).isDeleted())
        return CarServiceConverter.fromEntity(csr.getCarServiceByCarServiceName(name));
        else
            return  null;
    }

    @Override
    public List<CarServicePriceListDTO> getAllItems(String name) {
        List<CarServicePriceListDTO> retList = new ArrayList<>();
        List<CarServicePriceList> items = csPrice.findAllByCarS_CarServiceName(name);
        if (items!=null) {
            for (CarServicePriceList c : items) {
                retList.add(CarServicePriceListConverter.fromEntity(c));
            }
            return retList;
        }
        else
            return null;
    }

    @Override
    public String changeItem(String ime, Double cena, Long id) {
        String ret = "ERROR!";
        try {
            csPrice.modfyCarPriceListItem(ime, cena, id);
            ret = "SUCCESS!";
        }catch (Exception e){
                e.printStackTrace();
        }finally {
            return ret;
        }
        }

    @Override
    public String carServiceRate(CarServiceRateDTO csrDTO) {
        String ret = "ERROR!";
        try {
            CarServiceRate csra = CarServiceRateConverter.toEntity(csrDTO);
            csrr.save(csra);
            Double grade = csrr.getAvgRate(csrDTO.getServiceID());
            csr.rateSelectedCarService(grade,csrDTO.getServiceID());
            ret = "SUCCESS!";
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return ret;
        }
    }

    @Override
    public Double getAvgGrade(String name) {
        Double count = 0D;
        List<CarServiceRate> allRates= csrr.findAllByCarService_CarServiceName(name);
        for(CarServiceRate cr : allRates){
            count+=cr.getRate();
        }
        return count/allRates.size();
    }

    @Override
    public String modifyService(String name, String adress, String description, String location, Long id) {
        String ret = "ERROR";
        try {
            CarService old = csr.getCarServiceByCarServiceId(id);
            if(!name.equals(old.getCarServiceName())){
            if(!csr.existsByCarServiceName(name)){
                csr.modifyCarService(name,adress,description,location,id);
                ret = "SUCCESS";
            }
            else{
                ret = "Service name is already in use!";


            }
            }else{
                csr.modifyCarService(name,adress,description,location,id);
                ret = "SUCCESS";
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return ret;
        }


    }

    @Override
    public boolean isUserAbleToRateService(Long userID, Long serviceID) {

        List<CarReservation> carReservations = crr.checkIfEverReservedAnyCar(serviceID,userID);
        Boolean exist = csrr.existsByCarService_CarServiceIdAndUser_Id(serviceID,userID);
        if(carReservations.size()!=0 && !exist)
            return true;
        else
            return false;

    }

    @Override
    public List<CarServiceDTO> search(String address, String name) {
        List<CarService> services = csr.search(address,name);
        List<CarServiceDTO> output = new ArrayList<>();
        for (CarService service:services)
            output.add(CarServiceConverter.fromEntity(service));
        return output;
    }

    @Override
    public List<CarServiceDTO> getAll() {
        List<CarService> base = csr.findAll();
        List<CarServiceDTO> newList = new ArrayList<>();
        for (CarService cs : base) {
            newList.add(CarServiceConverter.fromEntity(cs));
        }
            return newList;


    }

}
