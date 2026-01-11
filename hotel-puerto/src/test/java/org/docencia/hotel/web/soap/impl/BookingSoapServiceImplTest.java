package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
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

public class BookingSoapServiceImplTest {

    @Mock
    private BookingDomain bookingDomain;

    @InjectMocks
    private BookingSoapServiceImpl bookingSoapService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookingById() {
        Booking booking = new Booking();
        booking.setId("B1");
        when(bookingDomain.getBooking("B1")).thenReturn(Optional.of(booking));

        Booking result = bookingSoapService.getBookingById("B1");

        assertNotNull(result);
        assertEquals("B1", result.getId());
        verify(bookingDomain).getBooking("B1");
    }

    @Test
    void testGetBookingByIdNotFound() {
        when(bookingDomain.getBooking("ERR")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingSoapService.getBookingById("ERR"));
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = Arrays.asList(new Booking(), new Booking());
        when(bookingDomain.getAllBookings()).thenReturn(bookings);

        List<Booking> result = bookingSoapService.getAllBookings();

        assertEquals(2, result.size());
        verify(bookingDomain).getAllBookings();
    }

    @Test
    void testGetBookingsByGuest() {
        List<Booking> bookings = Arrays.asList(new Booking());
        when(bookingDomain.getBookingsByGuest("G1")).thenReturn(bookings);

        List<Booking> result = bookingSoapService.getBookingsByGuest("G1");

        assertFalse(result.isEmpty());
        verify(bookingDomain).getBookingsByGuest("G1");
    }

    @Test
    void testGetBookingsByRoom() {
        List<Booking> bookings = Arrays.asList(new Booking());
        when(bookingDomain.getBookingsByRoom("R1")).thenReturn(bookings);

        List<Booking> result = bookingSoapService.getBookingsByRoom("R1");

        assertFalse(result.isEmpty());
        verify(bookingDomain).getBookingsByRoom("R1");
    }

    @Test
    void testGetRoomFromBooking() {
        Room room = new Room();
        room.setId("R101");
        when(bookingDomain.getRoomFromBooking("B1")).thenReturn(Optional.of(room));

        Room result = bookingSoapService.getRoomFromBooking("B1");

        assertNotNull(result);
        assertEquals("R101", result.getId());
        verify(bookingDomain).getRoomFromBooking("B1");
    }

    @Test
    void testGetGuestFromBooking() {
        Guest guest = new Guest();
        guest.setId("G1");
        when(bookingDomain.getGuestFromBooking("B1")).thenReturn(Optional.of(guest));

        Guest result = bookingSoapService.getGuestFromBooking("B1");

        assertNotNull(result);
        assertEquals("G1", result.getId());
        verify(bookingDomain).getGuestFromBooking("B1");
    }

    @Test
    void testSaveBooking() {
        Booking booking = new Booking();
        when(bookingDomain.createBooking(any(Booking.class))).thenReturn(booking);

        Booking result = bookingSoapService.saveBooking(booking);

        assertNotNull(result);
        verify(bookingDomain).createBooking(booking);
    }

    @Test
    void testUpdateBooking() {
        Booking booking = new Booking();
        when(bookingDomain.updateBooking(eq("B1"), any(Booking.class))).thenReturn(booking);

        Booking result = bookingSoapService.updateBooking("B1", booking);

        assertNotNull(result);
        verify(bookingDomain).updateBooking("B1", booking);
    }

    @Test
    void testCancelBooking() {
        doNothing().when(bookingDomain).cancelBooking("B1");

        bookingSoapService.cancelBooking("B1");

        verify(bookingDomain).cancelBooking("B1");
    }
}