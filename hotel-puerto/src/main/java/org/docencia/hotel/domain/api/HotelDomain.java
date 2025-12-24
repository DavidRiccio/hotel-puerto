package org.docencia.hotel.domain.api;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import java.util.List;
import java.util.Optional;

public interface HotelDomain {

    /**
     * Obtiene un hotel por id
     * 
     * @param id id del hotel
     * @return hotel o vacio
     */
    Optional<Hotel> getHotel(String id);

    /**
     * Obtiene todos los hoteles
     * 
     * @return lista de hoteles
     */
    List<Hotel> getAllHotels();

    /**
     * Obtiene las habitaciones de un hotel
     * 
     * @param hotelId id del hotel
     * @return lista de habitaciones
     */
    List<Room> getRoomsByHotel(String hotelId);

    /**
     * Crea un hotel
     * 
     * @param hotel hotel a crear
     * @return hotel creado
     */
    Hotel createHotel(Hotel hotel);

    /**
     * Actualiza un hotel
     * 
     * @param id    id del hotel
     * @param hotel datos actualizados
     * @return hotel actualizado
     */
    Hotel updateHotel(String id, Hotel hotel);

    /**
     * Elimina un hotel
     * 
     * @param id id del hotel
     */
    void deleteHotel(String id);
}
