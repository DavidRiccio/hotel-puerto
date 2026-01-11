package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.HotelService;
import org.docencia.hotel.service.api.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelDomainImplTest {

    @Mock
    private HotelService hotelService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private HotelDomainImpl hotelDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateHotel() {
        Hotel hotel = new Hotel();
        when(hotelService.save(any(Hotel.class))).thenReturn(hotel);

        Hotel result = hotelDomain.createHotel(hotel);

        assertNotNull(result);
        verify(hotelService).save(hotel);
    }

    @Test
    void testDeleteHotel() {
        doNothing().when(hotelService).deleteById("H1");

        hotelDomain.deleteHotel("H1");

        verify(hotelService).deleteById("H1");
    }

    @Test
    void testGetAllHotels() {
        List<Hotel> hotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelService.findAll()).thenReturn(hotels);

        List<Hotel> result = hotelDomain.getAllHotels();

        assertEquals(2, result.size());
        verify(hotelService).findAll();
    }

    @Test
    void testGetHotel() {
        Hotel hotel = new Hotel();
        hotel.setId("H1");
        when(hotelService.findById("H1")).thenReturn(Optional.of(hotel));

        Optional<Hotel> result = hotelDomain.getHotel("H1");

        assertTrue(result.isPresent());
        assertEquals("H1", result.get().getId());
        verify(hotelService).findById("H1");
    }

    @Test
    void testGetRoomsByHotel() {
        List<Room> rooms = Arrays.asList(new Room());
        when(roomService.findByHotelId("H1")).thenReturn(rooms);

        List<Room> result = hotelDomain.getRoomsByHotel("H1");

        assertFalse(result.isEmpty());
        verify(roomService).findByHotelId("H1");
    }

    @Test
    void testUpdateHotel() {
        Hotel hotel = new Hotel();
        when(hotelService.update(eq("H1"), any(Hotel.class))).thenReturn(hotel);

        Hotel result = hotelDomain.updateHotel("H1", hotel);

        assertNotNull(result);
        verify(hotelService).update("H1", hotel);
    }
}