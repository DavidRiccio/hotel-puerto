package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
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

public class GuestSoapServiceImplTest {

    @Mock
    private GuestDomain guestDomain;

    @InjectMocks
    private GuestSoapServiceImpl guestSoapService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGuestById() {
        Guest guest = new Guest();
        guest.setId("G1");
        when(guestDomain.getGuest("G1")).thenReturn(Optional.of(guest));

        Guest result = guestSoapService.getGuestById("G1");

        assertNotNull(result);
        assertEquals("G1", result.getId());
        verify(guestDomain).getGuest("G1");
    }

    @Test
    void testGetGuestByIdNotFound() {
        when(guestDomain.getGuest("ERR")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> guestSoapService.getGuestById("ERR"));
        verify(guestDomain).getGuest("ERR");
    }

    @Test
    void testGetAllGuests() {
        List<Guest> guests = Arrays.asList(new Guest(), new Guest());
        when(guestDomain.getAllGuests()).thenReturn(guests);

        List<Guest> result = guestSoapService.getAllGuests();

        assertEquals(2, result.size());
        verify(guestDomain).getAllGuests();
    }

    @Test
    void testSaveGuest() {
        Guest guest = new Guest();
        when(guestDomain.createGuest(any(Guest.class))).thenReturn(guest);

        Guest result = guestSoapService.saveGuest(guest);

        assertNotNull(result);
        verify(guestDomain).createGuest(guest);
    }

    @Test
    void testUpdateGuest() {
        Guest guest = new Guest();
        when(guestDomain.updateGuest(eq("G1"), any(Guest.class))).thenReturn(guest);

        Guest result = guestSoapService.updateGuest("G1", guest);

        assertNotNull(result);
        verify(guestDomain).updateGuest("G1", guest);
    }

    @Test
    void testDeleteGuest() {
        doNothing().when(guestDomain).deleteGuest("G1");

        guestSoapService.deleteGuest("G1");

        verify(guestDomain).deleteGuest("G1");
    }
}