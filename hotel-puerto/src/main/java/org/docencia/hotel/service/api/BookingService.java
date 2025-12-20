package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    
    /**
     * Busca todas las reservas
     * 
     * @return lista de reservas
     */
    List<Booking> findAll();
    
    /**
     * Busca una reserva por id
     * 
     * @param id id de la reserva
     * @return reserva o vacio
     */
    Optional<Booking> findById(String id);
    
    /**
     * Guarda una reserva
     * 
     * @param booking reserva a guardar
     * @return reserva guardada
     */
    Booking save(Booking booking);
    
    /**
     * Actualiza una reserva
     * 
     * @param id id de la reserva
     * @param booking datos actualizados
     * @return reserva actualizada
     */
    Booking update(String id, Booking booking);
    
    /**
     * Elimina una reserva
     * 
     * @param id id de la reserva
     */
    void deleteById(String id);
    
    /**
     * Busca reservas por huesped
     * 
     * @param guestId id del huesped
     * @return lista de reservas
     */
    List<Booking> findByGuestId(String guestId);
    
    /**
     * Busca reservas por habitacion
     * 
     * @param roomId id de la habitacion
     * @return lista de reservas
     */
    List<Booking> findByRoomId(String roomId);
}
