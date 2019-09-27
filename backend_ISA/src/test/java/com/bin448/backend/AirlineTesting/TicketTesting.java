package com.bin448.backend.AirlineTesting;

import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.DTOentity.PlaneTicketDTO;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.repository.PlaneTicketRepository;
import com.bin448.backend.repository.SeatRepository;
import com.bin448.backend.service.PlaneTicketService;
import com.bin448.backend.service.PlaneTicketServiceImpl;
import com.bin448.backend.service.SeatServiceImpl;
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
public class TicketTesting {

    @InjectMocks
    private PlaneTicketServiceImpl planeTicketService;

    @Mock
    private PlaneTicketRepository planeTicketRepository;

    @Mock
    private PlaneTicket planeTicketX;


    @Test
    @Transactional
    @Rollback()
    public void ticketCreationAndDelete() {
        PlaneTicketDTO planeTicketDTO = new PlaneTicketDTO(10L, false, 2L, "One way", "Economy", "1", null, null, null, 10L);

        PlaneTicket planeTicket = new PlaneTicket();
        planeTicket.setId(10L);

        Mockito.when(planeTicketRepository.findById(10L)).thenReturn(Optional.ofNullable(planeTicket));
        Assert.assertEquals("Ticket added!", planeTicketService.save(planeTicketDTO));

    }


    @Test
    public void findAllListTest() {
        Mockito.when(planeTicketRepository.findAll()).thenReturn(Arrays.asList(new PlaneTicket()));
        List<PlaneTicketDTO> planeTicketDTOS = planeTicketService.findAll();
        assertThat(planeTicketDTOS).hasSize(1);

        Mockito.verify(planeTicketRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(planeTicketRepository);
    }


}
