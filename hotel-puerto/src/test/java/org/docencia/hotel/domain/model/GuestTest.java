package org.docencia.hotel.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuestTest {

    private Guest guest;

    @BeforeEach
    void setUp() {
        guest = new Guest();
    }
    @Test
void testConstructorWithId() {
    Guest guest = new Guest("guest-123");
    assertEquals("guest-123", guest.getId());
    assertNull(guest.getFull_name());
    assertNull(guest.getEmail());
}

@Test
void testConstructorWithAllParameters() {
    Guest guest = new Guest("guest-123", "Juan Perez", "juan@test.com", "123456789", "pref-001");
    assertEquals("guest-123", guest.getId());
    assertEquals("Juan Perez", guest.getFull_name());
    assertEquals("juan@test.com", guest.getEmail());
    assertEquals("123456789", guest.getPhone());
    assertEquals("pref-001", guest.getPreferencesId());
}

    @Test
    void testGetId() {
        guest.setId("guest-123");
        assertEquals("guest-123", guest.getId());
    }

    @Test
    void testSetId() {
        guest.setId("guest-456");
        assertEquals("guest-456", guest.getId());
    }

    @Test
    void testGetFull_name() {
        guest.setFull_name("Juan Perez");
        assertEquals("Juan Perez", guest.getFull_name());
    }

    @Test
    void testSetFull_name() {
        guest.setFull_name("Maria Garcia");
        assertEquals("Maria Garcia", guest.getFull_name());
    }

    @Test
    void testGetEmail() {
        guest.setEmail("juan@test.com");
        assertEquals("juan@test.com", guest.getEmail());
    }

    @Test
    void testSetEmail() {
        guest.setEmail("maria@test.com");
        assertEquals("maria@test.com", guest.getEmail());
    }

    @Test
    void testGetPhone() {
        guest.setPhone("123456789");
        assertEquals("123456789", guest.getPhone());
    }

    @Test
    void testSetPhone() {
        guest.setPhone("987654321");
        assertEquals("987654321", guest.getPhone());
    }

    @Test
    void testGetPreferencesId() {
        guest.setPreferencesId("pref-001");
        assertEquals("pref-001", guest.getPreferencesId());
    }

    @Test
    void testSetPreferencesId() {
        guest.setPreferencesId("pref-002");
        assertEquals("pref-002", guest.getPreferencesId());
    }

    @Test
    void testEquals() {
        Guest guest1 = new Guest();
        guest1.setId("guest-123");
        guest1.setFull_name("Juan Perez");
        guest1.setEmail("juan@test.com");

        Guest guest2 = new Guest();
        guest2.setId("guest-123");
        guest2.setFull_name("Juan Perez");
        guest2.setEmail("juan@test.com");

        Guest guest3 = new Guest();
        guest3.setId("guest-456");

        assertEquals(guest1, guest2);
        assertNotEquals(guest1, guest3);
        assertNotEquals(guest1, null);
    }

    @Test
    void testHashCode() {
        Guest guest1 = new Guest();
        guest1.setId("guest-123");

        Guest guest2 = new Guest();
        guest2.setId("guest-123");

        assertEquals(guest1.hashCode(), guest2.hashCode());
    }
}
