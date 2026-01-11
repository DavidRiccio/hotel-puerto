package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.nosql.GuestPreferencesRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GuestServiceImplTest {

    @Autowired
    private GuestServiceImpl guestServiceImpl;

    @Autowired
    private static GuestJpaRepository guestRepository;

    @Autowired
    private static GuestPreferencesRepository preferencesRepository;

    private static Guest guest1;
    private static Guest guest2;
    private static GuestPreferences preferences1;

    @BeforeEach
    void setUp() {
        
      guest1 = new Guest();
        guest1.setFull_name("Juan Perez");
        guest1.setEmail("juan" + UUID.randomUUID() + "@test.com");
        guest1.setPhone("111222333");
        guest1 = guestServiceImpl.save(guest1);

        guest2 = new Guest();
        guest2.setFull_name("Maria Garcia");
        guest2.setEmail("maria" + UUID.randomUUID() + "@test.com");
        guest2.setPhone("444555666");
        guest2 = guestServiceImpl.save(guest2);
    }

    @AfterAll
    static void tearDown() {
        
        if (preferences1 != null && preferences1.getId() != null) {
            try {
                if (preferencesRepository.existsById(preferences1.getId())) {
                    preferencesRepository.deleteById(preferences1.getId());
                }
            } catch (Exception e) {
                
            }
        }

        
        if (guest1 != null && guest1.getId() != null) {
            try {
                if (guestRepository.existsById(guest1.getId())) {
                    guestRepository.deleteById(guest1.getId());
                }
            } catch (Exception e) {
                
            }
        }

        
        if (guest2 != null && guest2.getId() != null) {
            try {
                if (guestRepository.existsById(guest2.getId())) {
                    guestRepository.deleteById(guest2.getId());
                }
            } catch (Exception e) {
                
            }
        }
    }

    @Test
    void testFindAll() {
        List<Guest> guests = guestServiceImpl.findAll();
        assertNotNull(guests);
        assertTrue(guests.size() >= 2);
    }

    @Test
    void testFindById() {
        Optional<Guest> result = guestServiceImpl.findById(guest1.getId());
        assertTrue(result.isPresent());
        assertEquals("Juan Perez", result.get().getFull_name());
    }

    @Test
void testFindByEmail() {
    assertNotNull(guest1.getEmail());
    
    
    Optional<Guest> result = guestServiceImpl.findByEmail(guest1.getEmail());
    
    if (!result.isPresent()) {
        System.out.println("Email buscado: " + guest1.getEmail());
        List<Guest> allGuests = guestServiceImpl.findAll();
        System.out.println("Total guests: " + allGuests.size());
        allGuests.forEach(g -> System.out.println("Guest email: " + g.getEmail()));
    }
    
    assertTrue(result.isPresent(), "Guest should be found by email: " + guest1.getEmail());
    assertEquals(guest1.getId(), result.get().getId());
    assertEquals("Juan Perez", result.get().getFull_name());
}


    @Test
    void testFindByEmailNotFound() {
        Optional<Guest> result = guestServiceImpl.findByEmail("noexiste@test.com");
        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        assertNotNull(guest1.getId());
        assertEquals("Juan Perez", guest1.getFull_name());
    }

    @Test
    void testUpdate() {
        Guest updated = new Guest();
        updated.setFull_name("Juan Perez Updated");
        updated.setEmail("juan.updated@test.com");
        updated.setPhone("999888777");

        Guest result = guestServiceImpl.update(guest1.getId(), updated);

        assertEquals(guest1.getId(), result.getId());
        assertEquals("Juan Perez Updated", result.getFull_name());
    }

    @Test
    void testDeleteById() {
        String guestId = guest2.getId();
        guestServiceImpl.deleteById(guestId);

        Optional<Guest> result = guestServiceImpl.findById(guestId);
        assertFalse(result.isPresent());

        guest2 = null;
    }

    @Test
    void testSavePreferences() {
        
        GuestPreferences preferences = new GuestPreferences();
        preferences.setRoomType("Suite");
        preferences.setSpecialRequest("Vista al mar");

        
        preferences1 = guestServiceImpl.savePreferences(guest1.getId(), preferences);

        assertNotNull(preferences1);
        assertNotNull(preferences1.getId());
        assertEquals(guest1.getId(), preferences1.getGuestId());
        assertEquals("Suite", preferences1.getRoomType());
        assertEquals("Vista al mar", preferences1.getSpecialRequest());
    }

    @Test
    void testSavePreferencesGuestNotFound() {
        GuestPreferences preferences = new GuestPreferences();
        preferences.setRoomType("Double");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            guestServiceImpl.savePreferences("id-inexistente", preferences);
        });

        assertEquals("Guest not found", exception.getMessage());
    }

    @Test
    void testFindPreferencesByGuestId() {
        
        GuestPreferences preferences = new GuestPreferences();
        preferences.setRoomType("Double");
        preferences.setSpecialRequest("Cama extra");

        preferences1 = guestServiceImpl.savePreferences(guest1.getId(), preferences);

        
        Optional<GuestPreferences> result = guestServiceImpl.findPreferencesByGuestId(guest1.getId());

        assertTrue(result.isPresent());
        assertEquals(preferences1.getId(), result.get().getId());
        assertEquals("Double", result.get().getRoomType());
    }

    @Test
    void testFindPreferencesByGuestIdNotFound() {
        Optional<GuestPreferences> result = guestServiceImpl.findPreferencesByGuestId("id-sin-preferencias");
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdatePreferences() {
        
        GuestPreferences preferences = new GuestPreferences();
        preferences.setRoomType("Single");

        preferences1 = guestServiceImpl.savePreferences(guest1.getId(), preferences);

        
        GuestPreferences updated = new GuestPreferences();
        updated.setRoomType("Suite");
        updated.setSpecialRequest("Actualizado");

        GuestPreferences result = guestServiceImpl.savePreferences(guest1.getId(), updated);

        assertEquals(guest1.getId(), result.getGuestId());
        assertEquals("Suite", result.getRoomType());
        assertEquals("Actualizado", result.getSpecialRequest());
    }
}
