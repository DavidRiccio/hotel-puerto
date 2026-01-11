package org.docencia.hotel.persistence.jpa.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class BookingEntityTest {

    private BookingEntity bookingEntity;

    @BeforeEach
    void setUp() {
        bookingEntity = new BookingEntity();
    }

    @Test
    void testConstructorWithId() {
        BookingEntity booking = new BookingEntity("booking-123");
        assertEquals("booking-123", booking.getId());
        assertNull(booking.getCheck_in());
        assertNull(booking.getCheck_out());
    }

    @Test
    void testConstructorWithAllParameters() {
        GuestEntity guest = new GuestEntity("guest-789");
        RoomEntity room = new RoomEntity("room-456");
        BookingEntity booking = new BookingEntity("booking-123", room, guest, "2026-01-15", "2026-01-20");

        assertEquals("booking-123", booking.getId());
        assertNotNull(booking.getRoom());
        assertEquals("room-456", booking.getRoom().getId());
        assertNotNull(booking.getGuest());
        assertEquals("guest-789", booking.getGuest().getId());
        assertEquals("2026-01-15", booking.getCheck_in());
        assertEquals("2026-01-20", booking.getCheck_out());
    }

    @Test
    void testGetId() {
        bookingEntity.setId("booking-123");
        assertEquals("booking-123", bookingEntity.getId());
    }

    @Test
    void testSetId() {
        bookingEntity.setId("booking-456");
        assertEquals("booking-456", bookingEntity.getId());
    }

    @Test
    void testGetGuest() {
        GuestEntity guest = new GuestEntity("guest-789");
        bookingEntity.setGuest(guest);

        assertNotNull(bookingEntity.getGuest());
        assertEquals("guest-789", bookingEntity.getGuest().getId());
    }

    @Test
    void testSetGuest() {
        List<BookingEntity> bookings = new ArrayList<>();
        GuestEntity guest = new GuestEntity("guest-101", "Juan Perez", "juan@test.com", "123456789", "pref-001",
                bookings);
        bookingEntity.setGuest(guest);

        assertNotNull(bookingEntity.getGuest());
        assertEquals("guest-101", bookingEntity.getGuest().getId());
        assertEquals("Juan Perez", bookingEntity.getGuest().getFull_name());
    }

    @Test
    void testGetRoom() {
        RoomEntity room = new RoomEntity("room-456");
        bookingEntity.setRoom(room);

        assertNotNull(bookingEntity.getRoom());
        assertEquals("room-456", bookingEntity.getRoom().getId());
    }

    @Test
    void testSetRoom() {
        HotelEntity hotel = new HotelEntity("hotel-789");
        RoomEntity room = new RoomEntity("room-202", 202, "Double", 150.0f, hotel);
        bookingEntity.setRoom(room);

        assertNotNull(bookingEntity.getRoom());
        assertEquals("room-202", bookingEntity.getRoom().getId());
        assertEquals(202, bookingEntity.getRoom().getNumber());
    }

    @Test
    void testGetCheck_in() {
        bookingEntity.setCheck_in("2026-01-15");
        assertEquals("2026-01-15", bookingEntity.getCheck_in());
    }

    @Test
    void testSetCheck_in() {
        bookingEntity.setCheck_in("2026-02-01");
        assertEquals("2026-02-01", bookingEntity.getCheck_in());
    }

    @Test
    void testGetCheck_out() {
        bookingEntity.setCheck_out("2026-01-20");
        assertEquals("2026-01-20", bookingEntity.getCheck_out());
    }

    @Test
    void testSetCheck_out() {
        bookingEntity.setCheck_out("2026-02-10");
        assertEquals("2026-02-10", bookingEntity.getCheck_out());
    }

    @Test
    void testGuestNull() {
        bookingEntity.setGuest(null);
        assertNull(bookingEntity.getGuest());
    }

    @Test
    void testRoomNull() {
        bookingEntity.setRoom(null);
        assertNull(bookingEntity.getRoom());
    }

    @Test
    void testEquals() {
        BookingEntity booking1 = new BookingEntity();
        booking1.setId("booking-123");
        booking1.setCheck_in("2026-01-15");
        booking1.setCheck_out("2026-01-20");

        BookingEntity booking2 = new BookingEntity();
        booking2.setId("booking-123");
        booking2.setCheck_in("2026-01-15");
        booking2.setCheck_out("2026-01-20");

        BookingEntity booking3 = new BookingEntity();
        booking3.setId("booking-456");

        assertEquals(booking1, booking2);
        assertNotEquals(booking1, booking3);
        assertNotEquals(booking1, null);
    }

    @Test
    void testHashCode() {
        BookingEntity booking1 = new BookingEntity();
        booking1.setId("booking-123");

        BookingEntity booking2 = new BookingEntity();
        booking2.setId("booking-123");

        assertEquals(booking1.hashCode(), booking2.hashCode());
    }
}
