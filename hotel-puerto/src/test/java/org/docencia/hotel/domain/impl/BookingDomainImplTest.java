package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.BookingService;
import org.docencia.hotel.service.api.GuestService;
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

class BookingDomainImplTest {

    @Mock
    private BookingService bookingService;

    @Mock
    private RoomService roomService;

    @Mock
    private GuestService guestService;

    @InjectMocks
    private BookingDomainImpl bookingDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBooking() {
        Booking booking = new Booking();
        booking.setId("B1");
        when(bookingService.findById("B1")).thenReturn(Optional.of(booking));

        Optional<Booking> result = bookingDomain.getBooking("B1");

        assertTrue(result.isPresent());
        assertEquals("B1", result.get().getId());
        verify(bookingService).findById("B1");
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = Arrays.asList(new Booking(), new Booking());
        when(bookingService.findAll()).thenReturn(bookings);

        List<Booking> result = bookingDomain.getAllBookings();

        assertEquals(2, result.size());
        verify(bookingService).findAll();
    }

    @Test
    void testGetRoomFromBooking() {
        Booking booking = new Booking();
        booking.setRoomId("R101");
        Room room = new Room();
        room.setId("R101");

        when(bookingService.findById("B1")).thenReturn(Optional.of(booking));
        when(roomService.findById("R101")).thenReturn(Optional.of(room));

        Optional<Room> result = bookingDomain.getRoomFromBooking("B1");

        assertTrue(result.isPresent());
        assertEquals("R101", result.get().getId());
    }

    @Test
    void testGetGuestFromBooking() {
        Booking booking = new Booking();
        booking.setGuestId("G1");
        Guest guest = new Guest();
        guest.setId("G1");

        when(bookingService.findById("B1")).thenReturn(Optional.of(booking));
        when(guestService.findById("G1")).thenReturn(Optional.of(guest));

        Optional<Guest> result = bookingDomain.getGuestFromBooking("B1");

        assertTrue(result.isPresent());
        assertEquals("G1", result.get().getId());
    }

    @Test
    void testGetBookingsByGuest() {
        List<Booking> bookings = Arrays.asList(new Booking());
        when(bookingService.findByGuestId("G1")).thenReturn(bookings);

        List<Booking> result = bookingDomain.getBookingsByGuest("G1");

        assertFalse(result.isEmpty());
        verify(bookingService).findByGuestId("G1");
    }

    @Test
    void testGetBookingsByRoom() {
        List<Booking> bookings = Arrays.asList(new Booking());
        when(bookingService.findByRoomId("R1")).thenReturn(bookings);

        List<Booking> result = bookingDomain.getBookingsByRoom("R1");

        assertFalse(result.isEmpty());
        verify(bookingService).findByRoomId("R1");
    }

    @Test
    void testCreateBooking() {
        Booking booking = new Booking();
        when(bookingService.save(any(Booking.class))).thenReturn(booking);

        Booking result = bookingDomain.createBooking(booking);

        assertNotNull(result);
        verify(bookingService).save(booking);
    }

    @Test
    void testUpdateBooking() {
        Booking booking = new Booking();
        when(bookingService.update(eq("B1"), any(Booking.class))).thenReturn(booking);

        Booking result = bookingDomain.updateBooking("B1", booking);

        assertNotNull(result);
        verify(bookingService).update("B1", booking);
    }

    @Test
    void testCancelBooking() {
        doNothing().when(bookingService).deleteById("B1");

        bookingDomain.cancelBooking("B1");

        verify(bookingService).deleteById("B1");
    }
}