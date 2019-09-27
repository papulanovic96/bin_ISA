package com.bin448.backend.AirlineTesting;

import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.DTOentity.PlaneSeatDTO;
import com.bin448.backend.entity.Flight;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.repository.FlightRepository;
import com.bin448.backend.repository.SeatRepository;
import com.bin448.backend.service.FlightServiceImpl;
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
public class SeatTesting {

    @InjectMocks
    private SeatServiceImpl seatService;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private PlaneSeat planeSeatX;


    @Test
    @Transactional
    @Rollback()
    public void seatCreationAndDelete() {
        PlaneSeatDTO seatDTO = new PlaneSeatDTO(10L, false, 10L, 13486L, null);

        PlaneSeat planeSeat = new PlaneSeat();
        planeSeat.setSeatId(10L);

        Mockito.when(seatRepository.findById(10L)).thenReturn(Optional.ofNullable(planeSeat));
        Assert.assertEquals("Seat added!", seatService.save(seatDTO));

        Assert.assertEquals("Seat deleted!", seatService.delete(seatDTO));
    }


    @Test
    public void findAllListTest() {
        Mockito.when(seatRepository.findAll()).thenReturn(Arrays.asList(new PlaneSeat(false, null, null)));
        List<PlaneSeatDTO> planeSeatDTOList = seatService.findAll();
        assertThat(planeSeatDTOList).hasSize(1);

        Mockito.verify(seatRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(seatRepository);
    }

    @Test
    public void findOneTest() {
        Mockito.when(seatRepository.findById(10L)).thenReturn(Optional.of(planeSeatX));
        PlaneSeatDTO planeSeatDTO = seatService.findById(10L);
        assertThat(planeSeatDTO);
        Mockito.verify(seatRepository, Mockito.times(1)).findById(10L);
        Mockito.verifyNoMoreInteractions(seatRepository);
    }

}
