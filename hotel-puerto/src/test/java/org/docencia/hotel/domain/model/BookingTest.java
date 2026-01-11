package org.docencia.hotel.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
    }

    @Test
    void testConstructorWithId() {
        Booking booking = new Booking("booking-123");
        assertEquals("booking-123", booking.getId());
        assertNull(booking.getGuestId());
        assertNull(booking.getRoomId());
    }

    @Test
    void testConstructorWithAllParameters() {
        Booking booking = new Booking("booking-123","room-456" , "guest-789", "2026-01-15", "2026-01-20");
        assertEquals("booking-123", booking.getId());
        assertEquals("guest-789", booking.getGuestId());
        assertEquals("room-456", booking.getRoomId());
        assertEquals("2026-01-15", booking.getCheck_in());
        assertEquals("2026-01-20", booking.getCheck_out());
    }

    @Test
    void testGetId() {
        booking.setId("booking-123");
        assertEquals("booking-123", booking.getId());
    }

    @Test
    void testSetId() {
        booking.setId("booking-456");
        assertEquals("booking-456", booking.getId());
    }

    @Test
    void testGetRoomId() {
        booking.setRoomId("room-789");
        assertEquals("room-789", booking.getRoomId());
    }

    @Test
    void testSetRoomId() {
        booking.setRoomId("room-101");
        assertEquals("room-101", booking.getRoomId());
    }

    @Test
    void testGetGuestId() {
        booking.setGuestId("guest-001");
        assertEquals("guest-001", booking.getGuestId());
    }

    @Test
    void testSetGuestId() {
        booking.setGuestId("guest-002");
        assertEquals("guest-002", booking.getGuestId());
    }

    @Test
    void testGetCheck_in() {
        booking.setCheck_in("2026-03-01");
        assertEquals("2026-03-01", booking.getCheck_in());
    }

    @Test
    void testSetCheck_in() {
        booking.setCheck_in("2026-04-01");
        assertEquals("2026-04-01", booking.getCheck_in());
    }

    @Test
    void testGetCheck_out() {
        booking.setCheck_out("2026-03-05");
        assertEquals("2026-03-05", booking.getCheck_out());
    }

    @Test
    void testSetCheck_out() {
        booking.setCheck_out("2026-04-05");
        assertEquals("2026-04-05", booking.getCheck_out());
    }

    @Test
    void testEquals() {
        Booking booking1 = new Booking();
        booking1.setId("booking-123");
        booking1.setRoomId("room-101");
        booking1.setGuestId("guest-001");

        Booking booking2 = new Booking();
        booking2.setId("booking-123");
        booking2.setRoomId("room-101");
        booking2.setGuestId("guest-001");

        Booking booking3 = new Booking();
        booking3.setId("booking-456");

        assertEquals(booking1, booking2);
        assertNotEquals(booking1, booking3);
        assertNotEquals(booking1, null);
    }

    @Test
    void testHashCode() {
        Booking booking1 = new Booking();
        booking1.setId("booking-123");

        Booking booking2 = new Booking();
        booking2.setId("booking-123");

        assertEquals(booking1.hashCode(), booking2.hashCode());
    }
}
