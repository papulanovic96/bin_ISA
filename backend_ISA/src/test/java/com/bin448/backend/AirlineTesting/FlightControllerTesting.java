package com.bin448.backend.AirlineTesting;

import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.entity.DTOentity.FlightDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightControllerTesting {


    private static final String URL_PREFIX = "/flight";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
/*
    @Transactional
    @Rollback(true)
    @Test
    public void addFlightTesting() throws Exception {
        FlightDTO fDTO = new FlightDTO(10L, "BG","VL","2020-10-10", "2020-10-11", "09:40", "0.5h", 0L, "no", 0.0, 10L, null);

        String json = com.bin448.backend.TestUtil.json(fDTO);
        this.mockMvc.perform(post(URL_PREFIX+"/create").contentType(contentType).content(json)).andExpect(status().isOk());
    }*/


    @Transactional
    @Rollback(true)
    @Test
    public void deleteFlightTesting() throws Exception {
        mockMvc.perform(delete(URL_PREFIX + "/delete/11")).andExpect(status().isOk());
    }

    @Test
    public void findAllTesting() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/findAll")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].id").value(hasItem(3)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(11)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(12)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(13)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(14)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(15)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(16)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(17)));
    }
}
