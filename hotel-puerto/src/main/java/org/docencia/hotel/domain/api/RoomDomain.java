package org.docencia.hotel.domain.api;

import org.docencia.hotel.domain.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomDomain {

    /**
     * Obtiene una habitacion por id
     * 
     * @param id id de la habitacion
     * @return habitacion o vacio
     */
    Optional<Room> getRoom(String id);

    /**
     * Obtiene todas las habitaciones
     * 
     * @return lista de habitaciones
     */
    List<Room> getAllRooms();

    /**
     * Obtiene habitaciones por hotel
     * 
     * @param hotelId id del hotel
     * @return lista de habitaciones
     */
    List<Room> getRoomsByHotel(String hotelId);

    /**
     * Obtiene habitaciones por tipo
     * 
     * @param type tipo de habitacion
     * @return lista de habitaciones
     */
    List<Room> getRoomsByType(String type);

    /**
     * Crea una habitacion
     * 
     * @param room habitacion a crear
     * @return habitacion creada
     */
    Room createRoom(Room room);

    /**
     * Actualiza una habitacion
     * 
     * @param id   id de la habitacion
     * @param room datos actualizados
     * @return habitacion actualizada
     */
    Room updateRoom(String id, Room room);

    /**
     * Elimina una habitacion
     * 
     * @param id id de la habitacion
     */
    void deleteRoom(String id);
}
