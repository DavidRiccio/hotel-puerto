package org.docencia.hotel.persistence.jpa.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HotelEntityTest {

    private HotelEntity hotelEntity;

    @BeforeEach
    void setUp() {
        hotelEntity = new HotelEntity();
    }

    @Test
    void testConstructorWithId() {
        HotelEntity hotel = new HotelEntity("hotel-123");
        assertEquals("hotel-123", hotel.getId());
        assertNull(hotel.getName());
        assertNull(hotel.getAddress());
    }

    @Test
    void testConstructorWithAllParameters() {
        HotelEntity hotel = new HotelEntity("hotel-123", "Hotel Plaza", "Calle Mayor 123");
        assertEquals("hotel-123", hotel.getId());
        assertEquals("Hotel Plaza", hotel.getName());
        assertEquals("Calle Mayor 123", hotel.getAddress());
    }

    @Test
    void testGetId() {
        hotelEntity.setId("hotel-123");
        assertEquals("hotel-123", hotelEntity.getId());
    }

    @Test
    void testSetId() {
        hotelEntity.setId("hotel-456");
        assertEquals("hotel-456", hotelEntity.getId());
    }

    @Test
    void testGetName() {
        hotelEntity.setName("Hotel Plaza");
        assertEquals("Hotel Plaza", hotelEntity.getName());
    }

    @Test
    void testSetName() {
        hotelEntity.setName("Hotel Miramar");
        assertEquals("Hotel Miramar", hotelEntity.getName());
    }

    @Test
    void testGetAddress() {
        hotelEntity.setAddress("Calle Mayor 123");
        assertEquals("Calle Mayor 123", hotelEntity.getAddress());
    }

    @Test
    void testSetAddress() {
        hotelEntity.setAddress("Avenida del Mar 456");
        assertEquals("Avenida del Mar 456", hotelEntity.getAddress());
    }

    @Test
    void testEquals() {
        HotelEntity hotel1 = new HotelEntity();
        hotel1.setId("hotel-123");
        hotel1.setName("Hotel Plaza");
        hotel1.setAddress("Calle Mayor 123");

        HotelEntity hotel2 = new HotelEntity();
        hotel2.setId("hotel-123");
        hotel2.setName("Hotel Plaza");
        hotel2.setAddress("Calle Mayor 123");

        HotelEntity hotel3 = new HotelEntity();
        hotel3.setId("hotel-456");

        assertEquals(hotel1, hotel2);
        assertNotEquals(hotel1, hotel3);
        assertNotEquals(hotel1, null);
    }

    @Test
    void testHashCode() {
        HotelEntity hotel1 = new HotelEntity();
        hotel1.setId("hotel-123");

        HotelEntity hotel2 = new HotelEntity();
        hotel2.setId("hotel-123");

        assertEquals(hotel1.hashCode(), hotel2.hashCode());
    }
}
