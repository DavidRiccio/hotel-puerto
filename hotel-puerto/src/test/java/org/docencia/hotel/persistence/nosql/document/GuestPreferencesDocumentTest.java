package org.docencia.hotel.persistence.nosql.document;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestPreferencesDocumentTest {

    private GuestPreferencesDocument guestPreferencesDocument;

    @BeforeEach
    void setUp() {
        guestPreferencesDocument = new GuestPreferencesDocument();
    }

    @Test
    void testConstructorWithId() {
        GuestPreferencesDocument doc = new GuestPreferencesDocument("pref-123");
        assertEquals("pref-123", doc.getId());
        assertNull(doc.getGuestId());
        assertNull(doc.getRoomType());
    }

    @Test
    void testConstructorWithAllParameters() {
        GuestPreferencesDocument doc = new GuestPreferencesDocument("pref-123", "guest-789", "Suite", "Non-smoking room");
        
        assertEquals("pref-123", doc.getId());
        assertEquals("guest-789", doc.getGuestId());
        assertEquals("Suite", doc.getRoomType());
        assertEquals("Non-smoking room", doc.getSpecialRequests());
    }

    @Test
    void testGetId() {
        guestPreferencesDocument.setId("pref-123");
        assertEquals("pref-123", guestPreferencesDocument.getId());
    }

    @Test
    void testSetId() {
        guestPreferencesDocument.setId("pref-456");
        assertEquals("pref-456", guestPreferencesDocument.getId());
    }

    @Test
    void testGetGuestId() {
        guestPreferencesDocument.setGuestId("guest-789");
        assertEquals("guest-789", guestPreferencesDocument.getGuestId());
    }

    @Test
    void testSetGuestId() {
        guestPreferencesDocument.setGuestId("guest-101");
        assertEquals("guest-101", guestPreferencesDocument.getGuestId());
    }

    @Test
    void testGetRoomType() {
        guestPreferencesDocument.setRoomType("Suite");
        assertEquals("Suite", guestPreferencesDocument.getRoomType());
    }

    @Test
    void testSetRoomType() {
        guestPreferencesDocument.setRoomType("Deluxe");
        assertEquals("Deluxe", guestPreferencesDocument.getRoomType());
    }

    @Test
    void testGetSpecialRequests() {
        guestPreferencesDocument.setSpecialRequests("Non-smoking room");
        assertEquals("Non-smoking room", guestPreferencesDocument.getSpecialRequests());
    }

    @Test
    void testSetSpecialRequests() {
        guestPreferencesDocument.setSpecialRequests("Extra pillows and late check-out");
        assertEquals("Extra pillows and late check-out", guestPreferencesDocument.getSpecialRequests());
    }

    @Test
    void testSpecialRequestsNull() {
        guestPreferencesDocument.setSpecialRequests(null);
        assertNull(guestPreferencesDocument.getSpecialRequests());
    }

    @Test
    void testEquals() {
        GuestPreferencesDocument doc1 = new GuestPreferencesDocument();
        doc1.setId("pref-123");
        doc1.setGuestId("guest-789");
        doc1.setRoomType("Suite");

        GuestPreferencesDocument doc2 = new GuestPreferencesDocument();
        doc2.setId("pref-123");
        doc2.setGuestId("guest-789");
        doc2.setRoomType("Suite");

        GuestPreferencesDocument doc3 = new GuestPreferencesDocument();
        doc3.setId("pref-456");

        assertEquals(doc1, doc2);
        assertNotEquals(doc1, doc3);
        assertNotEquals(doc1, null);
    }

    @Test
    void testHashCode() {
        GuestPreferencesDocument doc1 = new GuestPreferencesDocument();
        doc1.setId("pref-123");

        GuestPreferencesDocument doc2 = new GuestPreferencesDocument();
        doc2.setId("pref-123");

        assertEquals(doc1.hashCode(), doc2.hashCode());
    }
}
