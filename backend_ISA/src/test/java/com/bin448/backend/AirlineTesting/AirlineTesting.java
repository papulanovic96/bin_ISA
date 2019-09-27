package com.bin448.backend.AirlineTesting;

import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.AirlineRepository;
import com.bin448.backend.repository.UserRepository;
import com.bin448.backend.service.AirlineServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
public class AirlineTesting {

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private Airline airlineX;


    @Test
    @Transactional
    @Rollback()
    public void airlineCreation() {
        AirlineDTO airlineDTO = new AirlineDTO();
        airlineDTO.setId(11L);
        airlineDTO.setName("JUG airlines");
        airlineDTO.setAddress("Beograd, Vuka Karadzica 19b");
        airlineDTO.setOfficeDestination("Zagreb, Luka 9a");
        airlineDTO.setDescription("Stara kompanija.");

        Airline airline = new Airline();
        airline.setOfficeDestination("Zagreb, Luka 9a");

        Mockito.when(airlineRepository.findById(11L)).thenReturn(Optional.ofNullable(airline));
        Assert.assertEquals("Airline added!", airlineService.save(airlineDTO));

        Assert.assertEquals("Airline deleted!", airlineService.delete(airlineDTO));
    }


    @Test
    public void findAllListTest() {
        Mockito.when(airlineRepository.findAll()).thenReturn(Arrays.asList(new Airline("YUG", "Novi Sad, bb", "No desc", "Beograd", null, null, null)));
        List<AirlineDTO> airlineList = airlineService.findAll();
        assertThat(airlineList).hasSize(1);

        Mockito.verify(airlineRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(airlineRepository);
    }

    @Test
    public void findOneTest() {
        Mockito.when(airlineRepository.findById(10L)).thenReturn(Optional.of(airlineX));
        AirlineDTO airLine = airlineService.findById(10L);
        assertThat(airLine);
        Mockito.verify(airlineRepository, Mockito.times(1)).findById(10L);
        Mockito.verifyNoMoreInteractions(airlineRepository);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void modifyTest() {
        Mockito.when(airlineRepository.findById(10L)).thenReturn(Optional.ofNullable(new Airline("YUG", "Novi Sad, bb", "No desc", "Beograd", null, null, null)));
        AirlineDTO airlineDTO = airlineService.findById(10L);

        airlineDTO.setName("JUGOTRON");
        airlineDTO.setAddress("Beograd, Ustanicka 55");
        airlineDTO.setDescription("Old place.");
        airlineDTO.setOfficeDestination("Vlasenica, Vuka Karadzica 11");

        //Mockito.when(airlineRepository.modifyAirline(10L, airlineDTO.getAddress(), airlineDTO.getDescription(), airlineDTO.getName(),airlineDTO.getOfficeDestination())).thenReturn(Optional.ofNullable(airlineDTO));

    }
}
