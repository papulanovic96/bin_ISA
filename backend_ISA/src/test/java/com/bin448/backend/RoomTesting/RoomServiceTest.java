package com.bin448.backend.RoomTesting;

import com.bin448.backend.converter.RoomConverter;
import com.bin448.backend.entity.DTOentity.HotelReservationDTO;
import com.bin448.backend.entity.DTOentity.RoomDTO;
import com.bin448.backend.entity.*;
import com.bin448.backend.repository.DiscountRepository;
import com.bin448.backend.repository.HotelRepository;
import com.bin448.backend.repository.HotelReservationRepository;
import com.bin448.backend.repository.RoomRepository;
import com.bin448.backend.service.HotelService;
import com.bin448.backend.service.NewRoomPriceService;
import com.bin448.backend.service.RoomServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private DiscountRepository discountRepository;

    @Mock
    HotelReservationRepository hotelReservationRepository;

    @Mock
    Hotel hotelMock;

    @Mock
    HotelReservation hotelReservationMock;

    @Mock
    HotelService hotelService;

    @Mock
    RoomType roomTypeMock;

    @Mock
    Room roomMock;

    @Mock
    NewRoomPriceService newRoomPriceService;

    @Mock
    RoomConverter roomConverter;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void testFindAll() {
        when(roomRepository.findByDeleted(Mockito.eq(false))).thenReturn(getRooms());
        List<RoomDTO> rooms = roomService.findAll();
        assertThat(rooms).hasSize(2);
        verify(roomRepository, times(1)).findByDeleted(false);
        verifyNoMoreInteractions(roomRepository);
    }

    @Test
    public void testFindRoomById() {
        when(roomRepository.findByNumberAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(roomMock));
        Room room = roomService.findRoomById(1l);
        Assert.assertEquals(roomMock, room);
        verify(roomRepository, times(1)).findByNumberAndDeleted(1l, false);
        verifyNoMoreInteractions(roomRepository);
    }

    @Test
    @Rollback(value = true)
    public void testRemoveRoom() {
        when(roomRepository.findByNumberAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(roomMock));
        when(hotelReservationRepository.getByRoom_Number(Mockito.eq(1l))).thenReturn(Arrays.asList(hotelReservationMock));
        when(roomRepository.save(any())).thenReturn(roomMock);
        Assert.assertEquals("SUCCESS", roomService.removeRoom(1l));
    }

    @Test
    @Rollback(value = true)
    public void testAddRoom() {
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(hotelMock));
        Assert.assertEquals("SUCCESS", roomService.addRoom(getRoomDTO()));
    }


    @Test
    @Rollback(true)
    public void testChangeRoom() {
        when(roomRepository.findByNumberAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(roomMock));
        when(hotelRepository.findByIdAndDeleted(Mockito.eq(1l), Mockito.eq(false))).thenReturn(Optional.of(hotelMock));
        when(hotelReservationRepository.getByRoom_Number(Mockito.eq(1l))).thenReturn(Arrays.asList(hotelReservationMock));
        when(roomRepository.save(any())).thenReturn(roomMock);
        Assert.assertEquals("SUCCESS", roomService.changeRoom(getRoomDTO(), 1l));
    }

    @Test
    @Rollback(true)
    public void testCheckIfThereIsAvailableRoom() {
        HotelReservationDTO reservationDTO = new HotelReservationDTO();
        reservationDTO.setArrivalDate(new Date());
        reservationDTO.setNumberOfNights(10);

        when(hotelReservationRepository.getByRoom_Number(Mockito.eq(1l))).thenReturn(getReservations());
        assertThat(roomService.checkIfThereIsAvailableRoom(getRoomDTO(), reservationDTO)).isEqualTo(true);
    }

    @Test
    @Rollback(true)
    public void testFindRoomsFromReservation() {
        HotelReservationDTO reservationDTO = new HotelReservationDTO();
        reservationDTO.setArrivalDate(new Date());
        reservationDTO.setNumberOfNights(10);

        RoomDTO room = new RoomDTO();
        room.setNumber(1l);
        room.setRoomType(1l);
        room.setHotelId(1l);
        room.setPricePerNight(110d);
        room.setReserved(false);
        room.setFloor(3);
        room.setNewPrice(0d);
        room.setAvgGrade(0d);

        List<RoomDTO> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomRepository.findByHotel_IdAndRoomType_IdAndDeleted(Mockito.eq(1l), Mockito.eq(1l), Mockito.eq(false))).thenReturn(getRooms());
        when(hotelReservationRepository.getByRoom_Number(Mockito.eq(1l))).thenReturn(getReservations());
        when(discountRepository.findByRoom_Number(any())).thenReturn(new ArrayList<Discount>());
        assertThat(roomService.findRoomsFromReservation(rooms, reservationDTO).size()).isEqualTo(1);
        List<RoomDTO> foundRoomArray = roomService.findRoomsFromReservation(rooms, reservationDTO).get(0);
        assertThat(foundRoomArray.size()).isEqualTo(0);
    }

    @Test
    @Rollback(true)
    public void testExistReservationWithRoomId() {
        when(hotelReservationRepository.getByRoom_Number(Mockito.eq(1l))).thenReturn(getReservations());
        assertThat(roomService.existReservationWithRoomId(1l)).isEqualTo(true);
    }

    public RoomDTO getRoomDTO() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomType(1l);
        roomDTO.setHotelId(1l);
        roomDTO.setPricePerNight(120d);
        roomDTO.setReserved(false);
        roomDTO.setFloor(3);
        roomDTO.setNewPrice(0d);
        roomDTO.setAvgGrade(0d);
        roomDTO.setNumber(1l);
        return roomDTO;
    }

    public List<Room> getRooms() {
        Room room = new Room();
        room.setNumber(1l);
        room.setRoomType(roomTypeMock);
        room.setHotel(hotelMock);
        room.setPricePerNight(112d);
        room.setReserved(false);
        room.setFloor(3);
        room.setNewPrice(0d);
        room.setAvgGrade(0d);

        Room room2 = new Room();
        room2.setNumber(2l);
        room2.setRoomType(roomTypeMock);
        room2.setHotel(hotelMock);
        room2.setPricePerNight(210d);
        room2.setReserved(false);
        room2.setFloor(3);
        room2.setNewPrice(0d);
        room2.setAvgGrade(0d);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room2);
        return rooms;
    }

    public List<HotelReservation> getReservations() {

        HotelReservation reservation = new HotelReservation();
        Calendar myCal = Calendar.getInstance();
        myCal.set(Calendar.YEAR, 2019);
        myCal.set(Calendar.MONTH, 7);
        myCal.set(Calendar.DAY_OF_MONTH, 21);
        Date theDate = myCal.getTime();
        reservation.setArrivalDate(theDate);
        myCal.set(Calendar.MONTH, 8);
        myCal.set(Calendar.DAY_OF_MONTH, 10);
        Date theDate2 = myCal.getTime();
        reservation.setReturnDate(theDate2);

        HotelReservation reservation2 = new HotelReservation();
        myCal.set(Calendar.MONTH, 10);
        myCal.set(Calendar.DAY_OF_MONTH, 30);
        Date theDate3 = myCal.getTime();
        reservation2.setArrivalDate(theDate3);
        myCal.set(Calendar.MONTH, 11);
        myCal.set(Calendar.DAY_OF_MONTH, 10);
        Date theDate4 = myCal.getTime();
        reservation2.setReturnDate(theDate4);

        List<HotelReservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        reservations.add(reservation2);
        return reservations;

    }

}
