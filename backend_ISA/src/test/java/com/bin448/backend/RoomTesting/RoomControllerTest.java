package com.bin448.backend.RoomTesting;


import com.bin448.backend.entity.DTOentity.RoomDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomControllerTest {

    private static final String URL_PREFIX = "/room";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Transactional
    @Rollback(true)
    @Test
    public void testAddRoom() throws Exception {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomType(1l);
        roomDTO.setHotelId(1l);
        roomDTO.setPricePerNight(112d);
        roomDTO.setReserved(false);
        roomDTO.setFloor(3);
        roomDTO.setNewPrice(0d);
        roomDTO.setAvgGrade(0d);

        String json = com.bin448.backend.TestUtil.json(roomDTO);
        this.mockMvc.perform(post(URL_PREFIX + "/addRoom").contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testRemoveRoom() throws Exception {
        this.mockMvc.perform(delete(URL_PREFIX + "/1")).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testChangeRoom() throws Exception {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomType(1l);
        roomDTO.setHotelId(1l);
        roomDTO.setPricePerNight(210d);
        roomDTO.setReserved(false);
        roomDTO.setFloor(3);
        roomDTO.setNewPrice(0d);
        roomDTO.setAvgGrade(0d);
        String json = com.bin448.backend.TestUtil.json(roomDTO);
        this.mockMvc.perform(put(URL_PREFIX + "/1").contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    public void testFindAllRooms() throws Exception {
        mockMvc.perform(get(URL_PREFIX)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].number").value(hasItem(1)))
                .andExpect(jsonPath("$.[*].number").value(hasItem(3)));
    }

    @Test
    public void testFindRoomDTOById() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/1")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.number").value(1))
                .andExpect(jsonPath("$.hotelId").value(1))
                .andExpect(jsonPath("$.roomType").value(1));

    }

    @Test
    public void testFindRoomsByHotelId() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/hotel/2")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].number").value(2))
                .andExpect(jsonPath("$.[*].roomType").value(2));

    }

    @Test
    public void testReservedRoom() throws Exception {
        MvcResult result = mockMvc.perform(get(URL_PREFIX + "/reserved/1")).andExpect(status().isOk()).andReturn();
        String resultAsString = result.getResponse().getContentAsString();
        assertThat(Boolean.parseBoolean(resultAsString)).isEqualTo(false);
    }
}
