package com.bin448.backend.service;

import com.bin448.backend.converter.CarServiceConverter;
import com.bin448.backend.converter.CarServicePriceListConverter;
import com.bin448.backend.converter.CarServiceRateConverter;
import com.bin448.backend.entity.CarRate;
import com.bin448.backend.entity.CarService;
import com.bin448.backend.entity.CarServicePriceList;
import com.bin448.backend.entity.CarServiceRate;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.repository.CarServicePriceListRepository;
import com.bin448.backend.repository.CarServiceRateRepository;
import com.bin448.backend.repository.CarServiceRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceServiceImpl implements CarServiceService {

    private CarServiceRepository csr;
    private CarServicePriceListRepository csPrice;
    private CarServiceRateRepository csrr;
    public CarServiceServiceImpl(CarServiceRateRepository csrr, CarServicePriceListRepository csPrice, CarServiceRepository csr){
        this.csrr = csrr;
        this.csPrice = csPrice;
        this.csr=csr;
    }




    @Override
    public String addCarService(CarServiceDTO cs) {
        String ret = "ADDING FAILED!";
        try {
            CarService cService = CarServiceConverter.toEntity(cs);
            csr.save(cService);
            ret = "CAR HAS BEEN SUCCESSFULLY ADDED";
        }
        catch (Exception e){
          e.printStackTrace();
        }
        finally {
            return ret;
        }
        }

    @Override
    public String removeCarService(String ime) {
        String ret = "REMOVING FAILED!";
        try {
            csr.deleteCarServiceByCarServiceName(ime);
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
        if(csr.getCarServiceByCarServiceName(name)!=null)
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
            CarServiceRate csr = CarServiceRateConverter.toEntity(csrDTO);
            csrr.save(csr);
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

}
