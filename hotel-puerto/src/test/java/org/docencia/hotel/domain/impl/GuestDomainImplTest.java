package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.service.api.BookingService;
import org.docencia.hotel.service.api.GuestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GuestDomainImplTest {

    @Mock
    private GuestService guestService;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private GuestDomainImpl guestDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGuest() {
        Guest guest = new Guest();
        guest.setId("1");
        when(guestService.findById("1")).thenReturn(Optional.of(guest));

        Optional<Guest> result = guestDomain.getGuest("1");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
        verify(guestService, times(1)).findById("1");
    }

    @Test
    void testGetAllGuests() {
        List<Guest> guests = Arrays.asList(new Guest(), new Guest());
        when(guestService.findAll()).thenReturn(guests);

        List<Guest> result = guestDomain.getAllGuests();

        assertEquals(2, result.size());
        verify(guestService, times(1)).findAll();
    }

    @Test
    void testGetGuestByEmail() {
        Guest guest = new Guest();
        guest.setEmail("test@hotel.com");
        when(guestService.findByEmail("test@hotel.com")).thenReturn(Optional.of(guest));

        Optional<Guest> result = guestDomain.getGuestByEmail("test@hotel.com");

        assertTrue(result.isPresent());
        assertEquals("test@hotel.com", result.get().getEmail());
    }

    @Test
    void testGetGuestPreferences() {
        GuestPreferences prefs = new GuestPreferences();
        when(guestService.findPreferencesByGuestId("1")).thenReturn(Optional.of(prefs));

        Optional<GuestPreferences> result = guestDomain.getGuestPreferences("1");

        assertTrue(result.isPresent());
        verify(guestService, times(1)).findPreferencesByGuestId("1");
    }

    @Test
    void testGetGuestBookings() {
        List<Booking> bookings = Arrays.asList(new Booking());
        when(bookingService.findByGuestId("1")).thenReturn(bookings);

        List<Booking> result = guestDomain.getGuestBookings("1");

        assertFalse(result.isEmpty());
        verify(bookingService, times(1)).findByGuestId("1");
    }

    @Test
    void testCreateGuest() {
        Guest guest = new Guest();
        when(guestService.save(guest)).thenReturn(guest);

        Guest result = guestDomain.createGuest(guest);

        assertNotNull(result);
        verify(guestService, times(1)).save(guest);
    }

    @Test
    void testUpdateGuest() {
        Guest guest = new Guest();
        when(guestService.update(eq("1"), any(Guest.class))).thenReturn(guest);

        Guest result = guestDomain.updateGuest("1", guest);

        assertNotNull(result);
        verify(guestService, times(1)).update("1", guest);
    }

    @Test
    void testSaveGuestPreferences() {
        GuestPreferences prefs = new GuestPreferences();
        when(guestService.savePreferences("1", prefs)).thenReturn(prefs);

        GuestPreferences result = guestDomain.saveGuestPreferences("1", prefs);

        assertNotNull(result);
        verify(guestService, times(1)).savePreferences("1", prefs);
    }

    @Test
    void testDeleteGuest() {
        doNothing().when(guestService).deleteById("1");

        guestDomain.deleteGuest("1");

        verify(guestService, times(1)).deleteById("1");
    }
}