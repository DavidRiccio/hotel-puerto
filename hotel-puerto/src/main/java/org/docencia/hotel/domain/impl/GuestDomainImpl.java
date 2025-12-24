package org.docencia.hotel.domain.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.service.api.BookingService;
import org.docencia.hotel.service.api.GuestService;
import org.springframework.stereotype.Service;

@Service
public class GuestDomainImpl implements GuestDomain {

    private final GuestService guestService;
    private final BookingService bookingService;

    public GuestDomainImpl(GuestService guestService, BookingService bookingService) {
        this.guestService = guestService;
        this.bookingService = bookingService;
    }

    @Override
    public Optional<Guest> getGuest(String id) {
        return guestService.findById(id);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestService.findAll();
    }

    @Override
    public Optional<Guest> getGuestByEmail(String email) {
        return guestService.findByEmail(email);
    }

    @Override
    public Optional<GuestPreferences> getGuestPreferences(String guestId) {
        return guestService.findPreferencesByGuestId(guestId);
    }

    @Override
    public List<Booking> getGuestBookings(String guestId) {
        return bookingService.findByGuestId(guestId);
    }

    @Override
    public Guest createGuest(Guest guest) {
        return guestService.save(guest);
    }

    @Override
    public Guest updateGuest(String id, Guest guest) {
        return guestService.update(id, guest);
    }

    @Override
    public GuestPreferences saveGuestPreferences(String guestId, GuestPreferences preferences) {
        return guestService.savePreferences(guestId, preferences);
    }

    @Override
    public void deleteGuest(String id) {
        guestService.deleteById(id);
    }
}
