package org.docencia.hotel.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestPreferencesTest {

    private GuestPreferences guestPreferences;

    @BeforeEach
    void setUp() {
        guestPreferences = new GuestPreferences();
    }

    @Test
    void testConstructorWithId() {
        GuestPreferences pref = new GuestPreferences("pref-123");
        assertEquals("pref-123", pref.getId());
        assertNull(pref.getGuestId());
        assertNull(pref.getRoomType());
    }

    @Test
    void testConstructorWithAllParameters() {
        GuestPreferences pref = new GuestPreferences("pref-123", "guest-789", "Suite", "Non-smoking room");
        assertEquals("pref-123", pref.getId());
        assertEquals("guest-789", pref.getGuestId());
        assertEquals("Suite", pref.getRoomType());
        assertEquals("Non-smoking room", pref.getSpecialRequest());
    }

    @Test
    void testGetId() {
        guestPreferences.setId("pref-123");
        assertEquals("pref-123", guestPreferences.getId());
    }

    @Test
    void testSetId() {
        guestPreferences.setId("pref-456");
        assertEquals("pref-456", guestPreferences.getId());
    }

    @Test
    void testGetGuestId() {
        guestPreferences.setGuestId("guest-789");
        assertEquals("guest-789", guestPreferences.getGuestId());
    }

    @Test
    void testSetGuestId() {
        guestPreferences.setGuestId("guest-101");
        assertEquals("guest-101", guestPreferences.getGuestId());
    }

    @Test
    void testGetRoomType() {
        guestPreferences.setRoomType("Suite");
        assertEquals("Suite", guestPreferences.getRoomType());
    }

    @Test
    void testSetRoomType() {
        guestPreferences.setRoomType("Deluxe");
        assertEquals("Deluxe", guestPreferences.getRoomType());
    }

    @Test
    void testGetSpecialRequest() {
        guestPreferences.setSpecialRequest("Non-smoking room");
        assertEquals("Non-smoking room", guestPreferences.getSpecialRequest());
    }

    @Test
    void testSetSpecialRequest() {
        guestPreferences.setSpecialRequest("Extra pillows");
        assertEquals("Extra pillows", guestPreferences.getSpecialRequest());
    }

    @Test
    void testEquals() {
        GuestPreferences pref1 = new GuestPreferences();
        pref1.setId("pref-123");
        pref1.setGuestId("guest-789");
        pref1.setRoomType("Suite");

        GuestPreferences pref2 = new GuestPreferences();
        pref2.setId("pref-123");
        pref2.setGuestId("guest-789");
        pref2.setRoomType("Suite");

        GuestPreferences pref3 = new GuestPreferences();
        pref3.setId("pref-456");

        assertEquals(pref1, pref2);
        assertNotEquals(pref1, pref3);
        assertNotEquals(pref1, null);
    }

    @Test
    void testHashCode() {
        GuestPreferences pref1 = new GuestPreferences();
        pref1.setId("pref-123");

        GuestPreferences pref2 = new GuestPreferences();
        pref2.setId("pref-123");

        assertEquals(pref1.hashCode(), pref2.hashCode());
    }
}
