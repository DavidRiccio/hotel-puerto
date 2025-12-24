package org.docencia.hotel.domain.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.BookingService;
import org.docencia.hotel.service.api.GuestService;
import org.docencia.hotel.service.api.RoomService;
import org.springframework.stereotype.Service;

@Service
public class BookingDomainImpl implements BookingDomain {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final GuestService guestService;

    public BookingDomainImpl(BookingService bookingService, RoomService roomService, GuestService guestService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
        this.guestService = guestService;
    }

    @Override
    public Optional<Booking> getBooking(String id) {
        return bookingService.findById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @Override
    public Optional<Room> getRoomFromBooking(String bookingId) {
        return bookingService.findById(bookingId)
                .flatMap(booking -> roomService.findById(booking.getRoomId()));
    }

    @Override
    public Optional<Guest> getGuestFromBooking(String bookingId) {
        return bookingService.findById(bookingId)
                .flatMap(booking -> guestService.findById(booking.getGuestId()));
    }

    @Override
    public List<Booking> getBookingsByGuest(String guestId) {
        return bookingService.findByGuestId(guestId);
    }

    @Override
    public List<Booking> getBookingsByRoom(String roomId) {
        return bookingService.findByRoomId(roomId);
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingService.save(booking);
    }

    @Override
    public Booking updateBooking(String id, Booking booking) {
        return bookingService.update(id, booking);
    }

    @Override
    public void cancelBooking(String id) {
        bookingService.deleteById(id);
    }
}
