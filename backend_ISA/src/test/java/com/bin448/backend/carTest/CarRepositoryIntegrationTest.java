package com.bin448.backend.carTest;

import com.bin448.backend.entity.Car;
import com.bin448.backend.entity.CarService;
import com.bin448.backend.repository.CarRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void testGetCarById() {
        // given
        Car car = new Car();
        CarService cs = new CarService();
        cs.setCarService_id(4l);
        car.setCarService(cs);
        car.setRegID("111");
        car.setCarId(100l);
        carRepository.save(car);

        // when
        Car found = carRepository.getCarByCarId(100l);
        Assert.assertEquals(car.getRegID(),found.getRegID());
        // then

    }
}
