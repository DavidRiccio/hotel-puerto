package org.docencia.hotel.persistence.jpa.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomEntityTest {

    private RoomEntity roomEntity;

    @BeforeEach
    void setUp() {
        roomEntity = new RoomEntity();
    }

   @Test
void testConstructorWithId() {
    RoomEntity room = new RoomEntity("room-123");
    assertEquals("room-123", room.getId());
    assertNull(room.getNumber());  // ✅ Integer puede ser null
    assertNull(room.getType());
}

@Test
void testConstructorWithAllParametersIncludingBookings() {
    HotelEntity hotel = new HotelEntity("hotel-789");
    List<BookingEntity> bookings = new ArrayList<>();
    BookingEntity booking1 = new BookingEntity("booking-123");
    BookingEntity booking2 = new BookingEntity("booking-456");
    bookings.add(booking1);
    bookings.add(booking2);

    RoomEntity room = new RoomEntity("room-123", 101, "Single", 100.0f, hotel);
    
    assertEquals("room-123", room.getId());
    assertEquals(101, room.getNumber());
    assertEquals("Single", room.getType());
    assertEquals(100.0f, room.getPrice_per_night());
    assertNotNull(room.getHotel());
    assertEquals("hotel-789", room.getHotel().getId());
}


    @Test
    void testGetId() {
        roomEntity.setId("room-123");
        assertEquals("room-123", roomEntity.getId());
    }

    @Test
    void testSetId() {
        roomEntity.setId("room-456");
        assertEquals("room-456", roomEntity.getId());
    }

    @Test
    void testGetNumber() {
        roomEntity.setNumber(101);
        assertEquals(101, roomEntity.getNumber());
    }

    @Test
    void testSetNumber() {
        roomEntity.setNumber(202);
        assertEquals(202, roomEntity.getNumber());
    }

    @Test
    void testGetType() {
        roomEntity.setType("Single");
        assertEquals("Single", roomEntity.getType());
    }

    @Test
    void testSetType() {
        roomEntity.setType("Double");
        assertEquals("Double", roomEntity.getType());
    }

    @Test
    void testGetPrice_per_night() {
        roomEntity.setPrice_per_night(100.0f);
        assertEquals(100.0f, roomEntity.getPrice_per_night());
    }

    @Test
    void testSetPrice_per_night() {
        roomEntity.setPrice_per_night(150.5f);
        assertEquals(150.5f, roomEntity.getPrice_per_night());
    }

    @Test
    void testGetHotel() {
        HotelEntity hotel = new HotelEntity("hotel-789");
        roomEntity.setHotel(hotel);
        
        assertNotNull(roomEntity.getHotel());
        assertEquals("hotel-789", roomEntity.getHotel().getId());
    }

    @Test
    void testSetHotel() {
        HotelEntity hotel = new HotelEntity("hotel-101", "Hotel Plaza", "Calle Mayor 123");
        roomEntity.setHotel(hotel);
        
        assertNotNull(roomEntity.getHotel());
        assertEquals("hotel-101", roomEntity.getHotel().getId());
        assertEquals("Hotel Plaza", roomEntity.getHotel().getName());
    }

    @Test
    void testGetBookings() {
        List<BookingEntity> bookings = new ArrayList<>();
        BookingEntity booking1 = new BookingEntity("booking-123");
        BookingEntity booking2 = new BookingEntity("booking-456");
        bookings.add(booking1);
        bookings.add(booking2);

        roomEntity.setBookings(bookings);
        
        assertNotNull(roomEntity.getBookings());
        assertEquals(2, roomEntity.getBookings().size());
        assertEquals("booking-123", roomEntity.getBookings().get(0).getId());
        assertEquals("booking-456", roomEntity.getBookings().get(1).getId());
    }

    @Test
    void testSetBookings() {
        List<BookingEntity> bookings = new ArrayList<>();
        BookingEntity booking = new BookingEntity("booking-789");
        bookings.add(booking);

        roomEntity.setBookings(bookings);
        
        assertNotNull(roomEntity.getBookings());
        assertEquals(1, roomEntity.getBookings().size());
        assertEquals("booking-789", roomEntity.getBookings().get(0).getId());
    }

    @Test
    void testBookingsEmptyList() {
        roomEntity.setBookings(new ArrayList<>());
        
        assertNotNull(roomEntity.getBookings());
        assertTrue(roomEntity.getBookings().isEmpty());
    }

    @Test
    void testHotelNull() {
        roomEntity.setHotel(null);
        assertNull(roomEntity.getHotel());
    }

    @Test
    void testEquals() {
        RoomEntity room1 = new RoomEntity();
        room1.setId("room-123");
        room1.setNumber(101);
        room1.setType("Single");

        RoomEntity room2 = new RoomEntity();
        room2.setId("room-123");
        room2.setNumber(101);
        room2.setType("Single");

        RoomEntity room3 = new RoomEntity();
        room3.setId("room-456");

        assertEquals(room1, room2);
        assertNotEquals(room1, room3);
        assertNotEquals(room1, null);
    }

    @Test
    void testHashCode() {
        RoomEntity room1 = new RoomEntity();
        room1.setId("room-123");

        RoomEntity room2 = new RoomEntity();
        room2.setId("room-123");

        assertEquals(room1.hashCode(), room2.hashCode());
    }
}
