package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.web.soap.BookingSoapService;

import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(
    endpointInterface = "org.docencia.hotel.web.soap.BookingSoapService",
    targetNamespace = "http://hotel.docencia.org/ws",
    serviceName = "BookingSoapService",
    portName = "BookingSoapPort"
)
public class BookingSoapServiceImpl implements BookingSoapService {

    private final BookingDomain bookingDomain;

    public BookingSoapServiceImpl(BookingDomain bookingDomain) {
        this.bookingDomain = bookingDomain;
    }

    @Override
    public Booking getBookingById(String id) {
        return bookingDomain.getBooking(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDomain.getAllBookings();
    }

    @Override
    public List<Booking> getBookingsByGuest(String guestId) {
        return bookingDomain.getBookingsByGuest(guestId);
    }

    @Override
    public List<Booking> getBookingsByRoom(String roomId) {
        return bookingDomain.getBookingsByRoom(roomId);
    }

    @Override
    public Room getRoomFromBooking(String bookingId) {
        return bookingDomain.getRoomFromBooking(bookingId)
                .orElseThrow(() -> new RuntimeException("Room not found for booking: " + bookingId));
    }

    @Override
    public Guest getGuestFromBooking(String bookingId) {
        return bookingDomain.getGuestFromBooking(bookingId)
                .orElseThrow(() -> new RuntimeException("Guest not found for booking: " + bookingId));
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingDomain.createBooking(booking);
    }

    @Override
    public Booking updateBooking(String id, Booking booking) {
        return bookingDomain.updateBooking(id, booking);
    }

    @Override
    public void cancelBooking(String id) {
        bookingDomain.cancelBooking(id);
    }
}
