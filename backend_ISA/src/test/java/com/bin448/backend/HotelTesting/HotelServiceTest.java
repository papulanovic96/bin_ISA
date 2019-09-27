package com.bin448.backend.HotelTesting;

import com.bin448.backend.converter.HotelConverter;
import com.bin448.backend.entity.Address;
import com.bin448.backend.entity.DTOentity.AddressDTO;
import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.repository.AddressRepository;
import com.bin448.backend.repository.HotelRepository;
import com.bin448.backend.repository.RoomRepository;
import com.bin448.backend.service.HotelServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class HotelServiceTest {

    @InjectMocks
    HotelServiceImpl hotelService;

    @Mock
    HotelConverter hotelConverter;

    @Mock
    HotelRepository hotelRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    RoomRepository roomRepository;

    @Mock
    Hotel hotelMock;

    @Mock
    Address addressMock;

    @Test
    public void testFindHotel() {
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(getHotel()));
        HotelDTO hotelDTO = hotelService.getHotel(1l);
        Assert.assertEquals(HotelConverter.fromEntity(getHotel()).getHotel_id(), hotelDTO.getHotel_id());
        verify(hotelRepository, times(1)).findByIdAndDeleted(1l, false);
        verifyNoMoreInteractions(hotelRepository);
    }

    @Test
    public void testFindAll() {
        when(hotelRepository.findByDeleted(Mockito.eq(false))).thenReturn(Arrays.asList(getHotel()));
        List<HotelDTO> hotels = hotelService.findAll();
        assertThat(hotels).hasSize(1);
        verify(hotelRepository, times(1)).findByDeleted(false);
        verifyNoMoreInteractions(hotelRepository);
    }

    @Test
    public void testFindAllAddresses() {
        when(addressRepository.findAll()).thenReturn(Arrays.asList(addressMock));
        List<AddressDTO> addresses = hotelService.findAllAddresses();
        assertThat(addresses).hasSize(1);
        verify(addressRepository, times(1)).findAll();
        verifyNoMoreInteractions(addressRepository);
    }

    @Test
    @Rollback(value = true)
    public void testAddHotel() {
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(hotelMock));
        Assert.assertEquals("SUCCESS", hotelService.addHotel(getHotelDTO()));
    }

    @Test
    @Rollback(true)
    public void testAddMenuItem() {
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(getHotel()));
        when(hotelRepository.save(getHotel())).thenReturn(getHotel());
        Assert.assertEquals("SUCCESS", hotelService.addMenuItem("WiFi", 12d, 1l));
    }

    @Test
    @Rollback(true)
    public void testRemoveMenuItem() {
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(getHotel()));
        when(hotelRepository.save(getHotel())).thenReturn(getHotel());
        Assert.assertEquals("SUCCESS", hotelService.removeMenuItem("WiFi", 1l));
    }

    @Test
    public void testGetMenu() {
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(getHotel()));
        hotelService.addMenuItem("WiFi", 12d, 1l);
        HashMap<String, Double> menu = hotelService.getMenu(1l);
        assertThat(menu.size()).isEqualTo(1);
    }

    @Test
    @Rollback(true)
    public void testRemoveHotel() {
        when(roomRepository.findByHotel_IdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Arrays.asList());
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(getHotel()));
        when(hotelRepository.save(getHotel())).thenReturn(getHotel());
        Assert.assertEquals(true, hotelService.removeHotel(1l));
    }

    @Test
    @Rollback(true)
    public void testChangeHotel() {
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(getHotel()));
        when(hotelRepository.save(getHotel())).thenReturn(getHotel());
        Assert.assertEquals("SUCCESS", hotelService.changeHotel(getHotelDTO(), 1l));
    }

    public Hotel getHotel() {
        Hotel hotel = new Hotel();
        hotel.setId(1l);
        hotel.setAddress(addressMock);
        return hotel;
    }

    public HotelDTO getHotelDTO() {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setHotel_id(1l);
        hotelDTO.setAddressId(1l);
        return hotelDTO;
    }

}
