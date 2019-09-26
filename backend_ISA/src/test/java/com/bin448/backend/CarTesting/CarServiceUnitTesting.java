package com.bin448.backend.CarTesting;

import com.bin448.backend.converter.CarConverter;
import com.bin448.backend.converter.CarServiceConverter;
import com.bin448.backend.converter.CarTypeConverter;
import com.bin448.backend.converter.RentACarBrenchOfficeConverter;
import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.CarDTO;
import com.bin448.backend.entity.DTOentity.CarServiceDTO;
import com.bin448.backend.entity.DTOentity.CarServicePriceListDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.repository.*;
import com.bin448.backend.service.CarReservationServiceImpl;
import com.bin448.backend.service.CarServiceImpl;
import com.bin448.backend.service.CarServiceServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarServiceUnitTesting {

    @InjectMocks
    private CarServiceServiceImpl carServiceService;
    @Mock
    private CarServiceRepository carServiceRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarConverter carConverter;
    @Mock
    private RentACarBrenchOfficeRespository brenchOfficeRespository;
    @Mock
    private CarServiceRateRepository carServiceRateRepository;
    @Mock
    private CarTypeRepository carTypeRepository;
    @Mock
    private CarRateRepository carR;
    @Mock
    private CarServicePriceListRepository servicePriceListRepository;
    @Mock
    private CarReservationRepository carReservationRepository;
    @Mock
    private RentACarBrenchOfficeConverter rentACarBrenchOfficeConverter;
    @InjectMocks
    private CarServiceImpl carService;
    @InjectMocks
    private CarReservationServiceImpl carReservationService;

    @Test
    public void testAddCarServiceAndDelete() {

        CarServiceDTO service = new CarServiceDTO();
        service.setCarServiceName("NAME");
        service.setId(2L);
        service.setCarServiceAddress("ADDRESS");
        CarService service1 = new CarService();
        service1.setCarServiceAddress("ADDRESS");
        Mockito.when(carServiceRepository.findById(2L)).thenReturn(Optional.ofNullable(service1));
        Assert.assertEquals("CAR SERVICE HAS BEEN SUCCESSFULLY ADDED", carServiceService.addCarService(service));
        Mockito.when(brenchOfficeRespository.deleteAllByCarService_CarServiceId(Mockito.any())).thenReturn(true);
        Assert.assertEquals(carServiceService.logicRemoveCarService(2L), "SUCCESS");
        Assert.assertEquals(carServiceRepository.findById(2L).get().getCarServiceAddress(), service1.getCarServiceAddress());
    }

    @Test
    public void testAddCarAndDelete() {
        Car cc = new Car();
        cc.setRegID("SA000");
        cc.setReserved(false);

        Mockito.when(carTypeRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(new CarType()));
        Mockito.when(carServiceRepository.getCarServiceByCarServiceId(Mockito.any())).thenReturn(new CarService());
        Mockito.when(carRepository.getCarByRegID(Mockito.any())).thenReturn(cc);

        CarDTO c = new CarDTO();
        c.setServiceId(1111L);
        c.setRegID("SA000");
        Assert.assertEquals("SUCCESS", carService.addCar(c));
        Assert.assertEquals("SUCCESS", carService.removeCar("SA000"));

    }

    @Test
    public void PriceListAddAndRemove() {
        CarServicePriceList carServicePriceList = new CarServicePriceList();
        CarServicePriceListDTO carServicePriceListDTO = new CarServicePriceListDTO();
        carServicePriceList.setCar(new Car());
        carServicePriceList.setCarS(new CarService());
        carServicePriceList.setIdCarServicePriceList(1L);
        carServicePriceList.setPrice(200D);
        Mockito.when(servicePriceListRepository.findByCarS_CarServiceIdAndCar_CarId(Mockito.any(), Mockito.any())).thenReturn(carServicePriceList);
        Assert.assertEquals("ALREADY EXISTS", carServiceService.addItem(carServicePriceList));
        Assert.assertEquals("SUCCESS!", carServiceService.deleteItem(1L));

    }

    @Test
    public void GetAvgGradeTest() {
        CarServiceRate carServiceRate = new CarServiceRate();
        carServiceRate.setRate(5D);
        CarServiceRate carServiceRate1 = new CarServiceRate();
        carServiceRate1.setRate(6D);
        CarServiceRate carServiceRate2 = new CarServiceRate();
        carServiceRate2.setRate(1D);

        List<CarServiceRate> rates = Arrays.asList(carServiceRate, carServiceRate1, carServiceRate2);
        Mockito.when(carServiceRateRepository.findAllByCarService_CarServiceName(Mockito.any())).thenReturn(rates);
        Assert.assertNotSame(4D, carServiceService.getAvgGrade("Service"));
    }

    @Test
    public void IsUserAbleToRateTest() {
        Car c = new Car();
        c.setRegID("SA123");
        c.setCarId(1L);
        User u = new User();
        u.setId(1L);
        u.setUsername("ivica");
        CarReservation carReservation = new CarReservation();
        carReservation.setCarId(c.getCarId());
        carReservation.setUserId(u.getId());
        CarRate carRate = new CarRate();
        carRate.setCar(c);
        carRate.setUser(u);
        Mockito.when(carReservationRepository.findAllByCarIdAndUserId(Mockito.any(), Mockito.any())).thenReturn(Arrays.asList(carReservation));
        Mockito.when(carR.findAllByCar_CarIdAndUser_Id(Mockito.any(), Mockito.any())).thenReturn(Arrays.asList(carRate));
        Assert.assertFalse(carReservationService.isUserAbleToRate(1L, 1L));
    }

    @Test
    public void DeleteReservationTest(){
        Car c = new Car();
        c.setRegID("SA123");
        c.setCarId(1L);
        User u = new User();
        u.setId(1L);
        u.setUsername("ivica");
        CarReservation carReservation = new CarReservation();
        carReservation.setCarId(c.getCarId());
        carReservation.setUserId(u.getId());
        Mockito.when(carReservationRepository.findByCarIdAndUserId(Mockito.any(),Mockito.any())).thenReturn(carReservation);
        Mockito.doNothing().when(carReservationRepository).deleteAllByCarIdAndUserId(Mockito.any(),Mockito.anyLong());
        Assert.assertEquals(carReservationService.removeReservation(1L,1L),"SUCCESS");
    }

    @Test
    public void CarServiceRate(){
        Car c = new Car();
        c.setRegID("SA123");
        c.setCarId(1L);
        User u = new User();
        u.setId(1L);
        u.setUsername("ivica");
        CarServiceRateDTO carServiceRateDTO = new CarServiceRateDTO();
        carServiceRateDTO.setUserID(1L);
        carServiceRateDTO.setServiceID(1L);
        carServiceRateDTO.setId(1L);
        carServiceRateDTO.setRate(6D);
        CarServiceRate carServiceRate = new CarServiceRate();
        carServiceRate.setRate(6D);
        carServiceRate.setUser(u);
        carServiceRate.setCarService(new CarService());
        carServiceRate.setId(1L);
        Mockito.when(carServiceRateRepository.save(Mockito.any())).thenReturn(carServiceRate);
        Mockito.when(carServiceRateRepository.getAvgRate(Mockito.any())).thenReturn(3D);
        Mockito.doNothing().when(carServiceRepository).rateSelectedCarService(Mockito.any(),Mockito.any());
        Assert.assertEquals(carServiceService.carServiceRate(new CarServiceRateDTO()),"ERROR!");
    }
}
