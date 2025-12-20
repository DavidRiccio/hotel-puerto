package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    
    /**
     * Busca todas las habitaciones
     * 
     * @return lista de habitaciones
     */
    List<Room> findAll();
    
    /**
     * Busca una habitacion por id
     * 
     * @param id id de la habitacion
     * @return habitacion o vacio
     */
    Optional<Room> findById(String id);
    
    /**
     * Guarda una habitacion
     * 
     * @param room habitacion a guardar
     * @return habitacion guardada
     */
    Room save(Room room);
    
    /**
     * Actualiza una habitacion
     * 
     * @param id id de la habitacion
     * @param room datos actualizados
     * @return habitacion actualizada
     */
    Room update(String id, Room room);
    
    /**
     * Elimina una habitacion
     * 
     * @param id id de la habitacion
     */
    void deleteById(String id);
    
    /**
     * Busca habitaciones por hotel
     * 
     * @param hotelId id del hotel
     * @return lista de habitaciones
     */
    List<Room> findByHotelId(String hotelId);
    
    /**
     * Busca habitaciones por tipo
     * 
     * @param type tipo de habitacion
     * @return lista de habitaciones
     */
    List<Room> findByType(String type);
}
