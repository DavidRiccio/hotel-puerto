package org.docencia.hotel.domain.api;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.Room;
import java.util.List;
import java.util.Optional;

public interface BookingDomain {

    /**
     * Obtiene una reserva por id
     * 
     * @param id id de la reserva
     * @return reserva o vacio
     */
    Optional<Booking> getBooking(String id);

    /**
     * Obtiene todas las reservas
     * 
     * @return lista de reservas
     */
    List<Booking> getAllBookings();

    /**
     * Obtiene la habitacion de una reserva
     * 
     * @param bookingId id de la reserva
     * @return habitacion o vacio
     */
    Optional<Room> getRoomFromBooking(String bookingId);

    /**
     * Obtiene el huesped de una reserva
     * 
     * @param bookingId id de la reserva
     * @return huesped o vacio
     */
    Optional<Guest> getGuestFromBooking(String bookingId);

    /**
     * Obtiene reservas por huesped
     * 
     * @param guestId id del huesped
     * @return lista de reservas
     */
    List<Booking> getBookingsByGuest(String guestId);

    /**
     * Obtiene reservas por habitacion
     * 
     * @param roomId id de la habitacion
     * @return lista de reservas
     */
    List<Booking> getBookingsByRoom(String roomId);

    /**
     * Crea una reserva con validacion
     * 
     * @param booking reserva a crear
     * @return reserva creada
     */
    Booking createBooking(Booking booking);

    /**
     * Actualiza una reserva
     * 
     * @param id      id de la reserva
     * @param booking datos actualizados
     * @return reserva actualizada
     */
    Booking updateBooking(String id, Booking booking);

    /**
     * Cancela una reserva
     * 
     * @param id id de la reserva
     */
    void cancelBooking(String id);
}
