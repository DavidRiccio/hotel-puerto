package org.docencia.hotel.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
    }

    @Test
    void testConstructorWithId() {
        Room room = new Room("room-123");
        assertEquals("room-123", room.getId());
        assertEquals(null, room.getNumber());
        assertNull(room.getType());
    }

    @Test
    void testConstructorWithAllParameters() {
        Room room = new Room("room-123", 101, "Single", 100.0f, "hotel-789");
        assertEquals("room-123", room.getId());
        assertEquals(101, room.getNumber());
        assertEquals("Single", room.getType());
        assertEquals(100.0f, room.getPrice_per_night());
        assertEquals("hotel-789", room.getHotelId());
    }

    @Test
    void testGetId() {
        room.setId("room-123");
        assertEquals("room-123", room.getId());
    }

    @Test
    void testSetId() {
        room.setId("room-456");
        assertEquals("room-456", room.getId());
    }

    @Test
    void testGetNumber() {
        room.setNumber(101);
        assertEquals(101, room.getNumber());
    }

    @Test
    void testSetNumber() {
        room.setNumber(202);
        assertEquals(202, room.getNumber());
    }

    @Test
    void testGetType() {
        room.setType("Single");
        assertEquals("Single", room.getType());
    }

    @Test
    void testSetType() {
        room.setType("Double");
        assertEquals("Double", room.getType());
    }

    @Test
    void testGetPrice_per_night() {
        room.setPrice_per_night(100.0f);
        assertEquals(100.0f, room.getPrice_per_night());
    }

    @Test
    void testSetPrice_per_night() {
        room.setPrice_per_night(150.5f);
        assertEquals(150.5f, room.getPrice_per_night());
    }

    @Test
    void testGetHotelId() {
        room.setHotelId("hotel-789");
        assertEquals("hotel-789", room.getHotelId());
    }

    @Test
    void testSetHotelId() {
        room.setHotelId("hotel-101");
        assertEquals("hotel-101", room.getHotelId());
    }

    @Test
    void testEquals() {
        Room room1 = new Room();
        room1.setId("room-123");
        room1.setNumber(101);
        room1.setType("Single");

        Room room2 = new Room();
        room2.setId("room-123");
        room2.setNumber(101);
        room2.setType("Single");

        Room room3 = new Room();
        room3.setId("room-456");

        assertEquals(room1, room2);
        assertNotEquals(room1, room3);
        assertNotEquals(room1, null);
    }

    @Test
    void testHashCode() {
        Room room1 = new Room();
        room1.setId("room-123");

        Room room2 = new Room();
        room2.setId("room-123");

        assertEquals(room1.hashCode(), room2.hashCode());
    }
}
