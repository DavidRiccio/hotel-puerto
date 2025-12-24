package org.docencia.hotel.domain.api;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import java.util.List;
import java.util.Optional;

public interface GuestDomain {

    /**
     * Obtiene un huesped por id
     * 
     * @param id id del huesped
     * @return huesped o vacio
     */
    Optional<Guest> getGuest(String id);

    /**
     * Obtiene todos los huespedes
     * 
     * @return lista de huespedes
     */
    List<Guest> getAllGuests();

    /**
     * Obtiene un huesped por email
     * 
     * @param email email del huesped
     * @return huesped o vacio
     */
    Optional<Guest> getGuestByEmail(String email);

    /**
     * Obtiene las preferencias de un huesped
     * 
     * @param guestId id del huesped
     * @return preferencias o vacio
     */
    Optional<GuestPreferences> getGuestPreferences(String guestId);

    /**
     * Obtiene las reservas de un huesped
     * 
     * @param guestId id del huesped
     * @return lista de reservas
     */
    List<Booking> getGuestBookings(String guestId);

    /**
     * Crea un huesped
     * 
     * @param guest huesped a crear
     * @return huesped creado
     */
    Guest createGuest(Guest guest);

    /**
     * Actualiza un huesped
     * 
     * @param id    id del huesped
     * @param guest datos actualizados
     * @return huesped actualizado
     */
    Guest updateGuest(String id, Guest guest);

    /**
     * Guarda las preferencias de un huesped
     * 
     * @param guestId     id del huesped
     * @param preferences preferencias a guardar
     * @return preferencias guardadas
     */
    GuestPreferences saveGuestPreferences(String guestId, GuestPreferences preferences);

    /**
     * Elimina un huesped
     * 
     * @param id id del huesped
     */
    void deleteGuest(String id);
}
