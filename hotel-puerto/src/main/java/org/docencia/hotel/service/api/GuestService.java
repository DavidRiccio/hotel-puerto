package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import java.util.List;
import java.util.Optional;

public interface GuestService {
    
    /**
     * Busca todos los huespedes
     * 
     * @return lista de huespedes
     */
    List<Guest> findAll();
    
    /**
     * Busca un huesped por id
     * 
     * @param id id del huesped
     * @return huesped o vacio
     */
    Optional<Guest> findById(String id);
    
    /**
     * Guarda un huesped
     * 
     * @param guest huesped a guardar
     * @return huesped guardado
     */
    Guest save(Guest guest);
    
    /**
     * Actualiza un huesped
     * 
     * @param id id del huesped
     * @param guest datos actualizados
     * @return huesped actualizado
     */
    Guest update(String id, Guest guest);
    
    /**
     * Elimina un huesped
     * 
     * @param id id del huesped
     */
    void deleteById(String id);
    
    /**
     * Busca un huesped por email
     * 
     * @param email email del huesped
     * @return huesped o vacio
     */
    Optional<Guest> findByEmail(String email);
    
    /**
     * Guarda las preferencias de un huesped 
     * 
     * @param guestId id del huesped
     * @param preferences preferencias a guardar
     * @return preferencias guardadas
     */
    GuestPreferences savePreferences(String guestId, GuestPreferences preferences);
    
    /**
     * Obtiene las preferencias de un huesped 
     * 
     * @param guestId id del huesped
     * @return preferencias o vacio
     */
    Optional<GuestPreferences> findPreferencesByGuestId(String guestId);
}
