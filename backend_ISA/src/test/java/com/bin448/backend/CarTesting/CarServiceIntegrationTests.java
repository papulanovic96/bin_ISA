package com.bin448.backend.CarTesting;

import com.bin448.backend.BackendApplication;
import com.bin448.backend.entity.*;
import com.bin448.backend.entity.DTOentity.CarRateDTO;
import com.bin448.backend.entity.DTOentity.CarServiceRateDTO;
import com.bin448.backend.repository.CarRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.ContentType;
import java.util.Base64;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarServiceIntegrationTests {
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Before
    public void setCredentials() {
        String userCredentials = "i:i";
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
        headers.add("Authorization", basicAuth);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testRateCar(){
        CarRateDTO cr = new CarRateDTO();
        cr.setCarID(1L);
        cr.setUserID(1L);
        cr.setId(null);
        cr.setRate(11D);
        HttpEntity<CarRateDTO> entity = new HttpEntity<>(cr, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("Car/rateCar"),
                HttpMethod.POST, entity, String.class);
        Assert.assertEquals("SUCCESS!", response.getBody());
        Assert.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Test
    public void GetAvgGradeServiceTest(){
        Double res = 5.0;
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Double> response = restTemplate.exchange(
                createURLWithPort("/carService/getAvgGrade/asd"),
                HttpMethod.GET, entity, Double.class);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
        Assert.assertEquals(response.getBody(),res);
    }

    @Test
    public void rateCarServiceTest(){
        CarServiceRateDTO carServiceRateDTO = new CarServiceRateDTO();
        carServiceRateDTO.setRate(5.0);
        carServiceRateDTO.setId(null);
        carServiceRateDTO.setServiceID(1L);
        carServiceRateDTO.setUserID(1L);
        HttpEntity<CarServiceRateDTO> entity = new HttpEntity<>(carServiceRateDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/carService/rateCarService"),
                HttpMethod.POST, entity, String.class);
        Assert.assertEquals("SUCCESS!",response.getBody());

    }

    @Test
    public void TryToRateCarService(){
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(
                createURLWithPort("/carService/tryToRate/1,1"),
                HttpMethod.GET, entity, Boolean.class);
        Assert.assertFalse(response.getBody());
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
    }

    @Test
    public void UpdateOfficeTest(){
        String userCredentials = "ivica:ivica";
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
        headers.remove("Authorization");
        headers.add("Authorization", basicAuth);
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/carService/office/modify/newAddress,1"),
                HttpMethod.GET, entity, String.class);
        Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCodeValue());
    }

}
