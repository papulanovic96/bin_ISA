package com.bin448.backend.AirlineTesting;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.Flight;
import com.bin448.backend.repository.AirlineRepository;
import com.bin448.backend.repository.FlightRepository;
import com.bin448.backend.service.AirlineServiceImpl;
import com.bin448.backend.service.FlightServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightTesting {

    @InjectMocks
    private FlightServiceImpl fService;

    @Mock
    private FlightRepository fRepository;

    @Mock
    private Flight flightX;


    @Test
    @Transactional
    @Rollback()
    public void flightCreationAndDelete() {
        FlightDTO fDTO = new FlightDTO(10L, "BG","VL","2020-10-10", "2020-10-11", "09:40", "0.5h", 0L, "no", 0.0, 10L, null);

        Flight flight = new Flight();
        flight.setId(10L);

        Mockito.when(fRepository.findById(10L)).thenReturn(Optional.ofNullable(flight));
        Assert.assertEquals("Flight added!", fService.save(fDTO));

        Assert.assertEquals("Flight deleted!", fService.delete(fDTO));
    }


    @Test
    public void findAllListTest() {
        Mockito.when(fRepository.findAll()).thenReturn(Arrays.asList(new Flight(10L, "BG","VL","2020-10-10", "2020-10-11", "09:40", "0.5h", 0L, "no", 0.0, null, null)));
        List<FlightDTO> flightDTOS = fService.findAll();
        assertThat(flightDTOS).hasSize(1);

        Mockito.verify(fRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(fRepository);
    }

    @Test
    public void findOneTest() {
        Mockito.when(fRepository.findById(10L)).thenReturn(Optional.of(flightX));
        FlightDTO Fdto = fService.findById(10L);
        assertThat(Fdto);
        Mockito.verify(fRepository, Mockito.times(1)).findById(10L);
        Mockito.verifyNoMoreInteractions(fRepository);
    }

    /*@Test
    @Transactional
    @Rollback(true)
    public void modifyTest() {
        Mockito.when(fRepository.findById(10L)).thenReturn(Optional.ofNullable(new Flight(10L, "BG","VL","2020-10-10", "2020-10-11", "09:40", "0.5h", 0L, "no", 0.0, null, null)));
        FlightDTO flightDTO = fService.findById(10L);

    }*/
}
