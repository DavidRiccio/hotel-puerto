package org.docencia.hotel.persistence.jpa.entity;

import org.docencia.hotel.domain.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuestEntityTest {

    private GuestEntity guestEntity;

    @BeforeEach
    void setUp() {
        guestEntity = new GuestEntity();
    }

    @Test
    void testConstructorWithId() {
        GuestEntity guest = new GuestEntity("guest-123");
        assertEquals("guest-123", guest.getId());
        assertNull(guest.getFull_name());
        assertNull(guest.getEmail());
    }

    @Test
void testConstructorWithAllParameters() {
    List<BookingEntity> bookings = new ArrayList<>();
    GuestEntity guest = new GuestEntity("guest-123", "Juan Perez", "juan@test.com", "123456789", "pref-001", bookings);
    
    assertEquals("guest-123", guest.getId());
    assertEquals("Juan Perez", guest.getFull_name());
    assertEquals("juan@test.com", guest.getEmail());
    assertEquals("123456789", guest.getPhone());
    assertEquals("pref-001", guest.getPreferencesId());
    assertNotNull(guest.getBookings());
    assertTrue(guest.getBookings().isEmpty());
}


    @Test
    void testGetId() {
        guestEntity.setId("guest-123");
        assertEquals("guest-123", guestEntity.getId());
    }

    @Test
    void testSetId() {
        guestEntity.setId("guest-456");
        assertEquals("guest-456", guestEntity.getId());
    }

    @Test
    void testGetFull_name() {
        guestEntity.setFull_name("Juan Perez");
        assertEquals("Juan Perez", guestEntity.getFull_name());
    }

    @Test
    void testSetFull_name() {
        guestEntity.setFull_name("Maria Garcia");
        assertEquals("Maria Garcia", guestEntity.getFull_name());
    }

    @Test
    void testGetEmail() {
        guestEntity.setEmail("juan@test.com");
        assertEquals("juan@test.com", guestEntity.getEmail());
    }

    @Test
    void testSetEmail() {
        guestEntity.setEmail("maria@test.com");
        assertEquals("maria@test.com", guestEntity.getEmail());
    }

    @Test
    void testGetPhone() {
        guestEntity.setPhone("123456789");
        assertEquals("123456789", guestEntity.getPhone());
    }

    @Test
    void testSetPhone() {
        guestEntity.setPhone("987654321");
        assertEquals("987654321", guestEntity.getPhone());
    }

    @Test
    void testGetPreferencesId() {
        guestEntity.setPreferencesId("pref-001");
        assertEquals("pref-001", guestEntity.getPreferencesId());
    }

    @Test
    void testSetPreferencesId() {
        guestEntity.setPreferencesId("pref-002");
        assertEquals("pref-002", guestEntity.getPreferencesId());
    }

    @Test
    void testGetBookings() {
        List<BookingEntity> bookings = new ArrayList<>();
        BookingEntity booking1 = new BookingEntity("booking-123");
        BookingEntity booking2 = new BookingEntity("booking-456");
        bookings.add(booking1);
        bookings.add(booking2);

        guestEntity.setBookings(bookings);
        
        assertNotNull(guestEntity.getBookings());
        assertEquals(2, guestEntity.getBookings().size());
        assertEquals("booking-123", guestEntity.getBookings().get(0).getId());
        assertEquals("booking-456", guestEntity.getBookings().get(1).getId());
    }

    @Test
    void testSetBookings() {
        List<BookingEntity> bookings = new ArrayList<>();
        BookingEntity booking = new BookingEntity("booking-789");
        bookings.add(booking);

        guestEntity.setBookings(bookings);
        
        assertNotNull(guestEntity.getBookings());
        assertEquals(1, guestEntity.getBookings().size());
        assertEquals("booking-789", guestEntity.getBookings().get(0).getId());
    }

    @Test
    void testBookingsEmptyList() {
        guestEntity.setBookings(new ArrayList<>());
        
        assertNotNull(guestEntity.getBookings());
        assertTrue(guestEntity.getBookings().isEmpty());
    }

    @Test
    void testEquals() {
        GuestEntity guest1 = new GuestEntity();
        guest1.setId("guest-123");
        guest1.setFull_name("Juan Perez");
        guest1.setEmail("juan@test.com");

        GuestEntity guest2 = new GuestEntity();
        guest2.setId("guest-123");
        guest2.setFull_name("Juan Perez");
        guest2.setEmail("juan@test.com");

        GuestEntity guest3 = new GuestEntity();
        guest3.setId("guest-456");

        assertEquals(guest1, guest2);
        assertNotEquals(guest1, guest3);
        assertNotEquals(guest1, null);
    }

    @Test
    void testHashCode() {
        GuestEntity guest1 = new GuestEntity();
        guest1.setId("guest-123");

        GuestEntity guest2 = new GuestEntity();
        guest2.setId("guest-123");

        assertEquals(guest1.hashCode(), guest2.hashCode());
    }
}
