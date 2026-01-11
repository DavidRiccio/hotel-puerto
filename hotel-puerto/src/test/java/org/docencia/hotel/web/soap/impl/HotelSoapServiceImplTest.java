package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
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

public class HotelSoapServiceImplTest {

    @Mock
    private HotelDomain hotelDomain;

    @InjectMocks
    private HotelSoapServiceImpl hotelSoapService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHotelById() {
        Hotel hotel = new Hotel();
        hotel.setId("H1");
        when(hotelDomain.getHotel("H1")).thenReturn(Optional.of(hotel));

        Hotel result = hotelSoapService.getHotelById("H1");

        assertNotNull(result);
        assertEquals("H1", result.getId());
        verify(hotelDomain).getHotel("H1");
    }

    @Test
    void testGetHotelByIdNotFound() {
        when(hotelDomain.getHotel("NOT_FOUND")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> hotelSoapService.getHotelById("NOT_FOUND"));
    }

    @Test
    void testGetAllHotels() {
        List<Hotel> hotels = Arrays.asList(new Hotel(), new Hotel());
        when(hotelDomain.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelSoapService.getAllHotels();

        assertEquals(2, result.size());
        verify(hotelDomain).getAllHotels();
    }

    @Test
    void testGetRoomsByHotel() {
        List<Room> rooms = Arrays.asList(new Room());
        when(hotelDomain.getRoomsByHotel("H1")).thenReturn(rooms);

        List<Room> result = hotelSoapService.getRoomsByHotel("H1");

        assertFalse(result.isEmpty());
        verify(hotelDomain).getRoomsByHotel("H1");
    }

    @Test
    void testSaveHotel() {
        Hotel hotel = new Hotel();
        when(hotelDomain.createHotel(any(Hotel.class))).thenReturn(hotel);

        Hotel result = hotelSoapService.saveHotel(hotel);

        assertNotNull(result);
        verify(hotelDomain).createHotel(hotel);
    }

    @Test
    void testUpdateHotel() {
        Hotel hotel = new Hotel();
        when(hotelDomain.updateHotel(eq("H1"), any(Hotel.class))).thenReturn(hotel);

        Hotel result = hotelSoapService.updateHotel("H1", hotel);

        assertNotNull(result);
        verify(hotelDomain).updateHotel("H1", hotel);
    }

    @Test
    void testDeleteHotel() {
        doNothing().when(hotelDomain).deleteHotel("H1");

        hotelSoapService.deleteHotel("H1");

        verify(hotelDomain).deleteHotel("H1");
    }
}