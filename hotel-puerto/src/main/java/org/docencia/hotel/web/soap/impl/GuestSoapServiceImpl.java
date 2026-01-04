package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.web.soap.GuestSoapService;

import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(
    endpointInterface = "org.docencia.hotel.web.soap.GuestSoapService",
    targetNamespace = "http://hotel.docencia.org/ws",
    serviceName = "GuestSoapService",
    portName = "GuestSoapPort"
)
public class GuestSoapServiceImpl implements GuestSoapService {

    private final GuestDomain guestDomain;

    public GuestSoapServiceImpl(GuestDomain guestDomain) {
        this.guestDomain = guestDomain;
    }

    @Override
    public Guest getGuestById(String id) {
        return guestDomain.getGuest(id)
                .orElseThrow(() -> new RuntimeException("Guest not found: " + id));
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestDomain.getAllGuests();
    }

    @Override
    public Guest saveGuest(Guest guest) {
        return guestDomain.createGuest(guest);
    }

    @Override
    public Guest updateGuest(String id, Guest guest) {
        return guestDomain.updateGuest(id, guest);
    }

    @Override
    public void deleteGuest(String id) {
        guestDomain.deleteGuest(id);
    }
}
