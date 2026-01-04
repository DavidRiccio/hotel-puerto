package org.docencia.hotel.web.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.Room;

@WebService(
    name = "BookingSoapService",
    targetNamespace = "http://hotel.docencia.org/ws"
)
public interface BookingSoapService {

    @WebMethod(operationName = "GetBookingById")
    @WebResult(name = "booking")
    Booking getBookingById(@WebParam(name = "id") String id);

    @WebMethod(operationName = "GetAllBookings")
    @WebResult(name = "bookings")
    List<Booking> getAllBookings();

    @WebMethod(operationName = "GetBookingsByGuest")
    @WebResult(name = "bookings")
    List<Booking> getBookingsByGuest(@WebParam(name = "guestId") String guestId);

    @WebMethod(operationName = "GetBookingsByRoom")
    @WebResult(name = "bookings")
    List<Booking> getBookingsByRoom(@WebParam(name = "roomId") String roomId);

    @WebMethod(operationName = "GetRoomFromBooking")
    @WebResult(name = "room")
    Room getRoomFromBooking(@WebParam(name = "bookingId") String bookingId);

    @WebMethod(operationName = "GetGuestFromBooking")
    @WebResult(name = "guest")
    Guest getGuestFromBooking(@WebParam(name = "bookingId") String bookingId);

    @WebMethod(operationName = "SaveBooking")
    @WebResult(name = "booking")
    Booking saveBooking(@WebParam(name = "booking") Booking booking);

    @WebMethod(operationName = "UpdateBooking")
    @WebResult(name = "booking")
    Booking updateBooking(
        @WebParam(name = "id") String id,
        @WebParam(name = "booking") Booking booking
    );

    @WebMethod(operationName = "CancelBooking")
    void cancelBooking(@WebParam(name = "id") String id);
}
