package org.docencia.hotel.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
    }

    @Test
    void testConstructorWithId() {
        Hotel hotel = new Hotel("hotel-123");
        assertEquals("hotel-123", hotel.getId());
        assertNull(hotel.getName());
        assertNull(hotel.getAddress());
    }

    @Test
    void testConstructorWithAllParameters() {
        Hotel hotel = new Hotel("hotel-123", "Hotel Plaza", "Calle Mayor 123");
        assertEquals("hotel-123", hotel.getId());
        assertEquals("Hotel Plaza", hotel.getName());
        assertEquals("Calle Mayor 123", hotel.getAddress());
    }

    @Test
    void testGetId() {
        hotel.setId("hotel-123");
        assertEquals("hotel-123", hotel.getId());
    }

    @Test
    void testSetId() {
        hotel.setId("hotel-456");
        assertEquals("hotel-456", hotel.getId());
    }

    @Test
    void testGetName() {
        hotel.setName("Hotel Plaza");
        assertEquals("Hotel Plaza", hotel.getName());
    }

    @Test
    void testSetName() {
        hotel.setName("Hotel Miramar");
        assertEquals("Hotel Miramar", hotel.getName());
    }

    @Test
    void testGetAddress() {
        hotel.setAddress("Calle Mayor 123");
        assertEquals("Calle Mayor 123", hotel.getAddress());
    }

    @Test
    void testSetAddress() {
        hotel.setAddress("Avenida del Mar 456");
        assertEquals("Avenida del Mar 456", hotel.getAddress());
    }

    @Test
    void testEquals() {
        Hotel hotel1 = new Hotel();
        hotel1.setId("hotel-123");
        hotel1.setName("Hotel Plaza");
        hotel1.setAddress("Calle Mayor 123");

        Hotel hotel2 = new Hotel();
        hotel2.setId("hotel-123");
        hotel2.setName("Hotel Plaza");
        hotel2.setAddress("Calle Mayor 123");

        Hotel hotel3 = new Hotel();
        hotel3.setId("hotel-456");

        assertEquals(hotel1, hotel2);
        assertNotEquals(hotel1, hotel3);
        assertNotEquals(hotel1, null);
    }

    @Test
    void testHashCode() {
        Hotel hotel1 = new Hotel();
        hotel1.setId("hotel-123");

        Hotel hotel2 = new Hotel();
        hotel2.setId("hotel-123");

        assertEquals(hotel1.hashCode(), hotel2.hashCode());
    }
}
