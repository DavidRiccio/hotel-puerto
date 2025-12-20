package org.docencia.hotel.service.api;

import org.docencia.hotel.domain.model.Hotel;
import java.util.List;
import java.util.Optional;

public interface HotelService {
    
    /**
     * Busca todos los hoteles
     * 
     * @return lista de hoteles
     */
    List<Hotel> findAll();
    
    /**
     * Busca un hotel por id
     * 
     * @param id id del hotel
     * @return hotel o vacio
     */
    Optional<Hotel> findById(String id);
    
    /**
     * Guarda un hotel
     * 
     * @param hotel hotel a guardar
     * @return hotel guardado
     */
    Hotel save(Hotel hotel);
    
    /**
     * Actualiza un hotel
     * 
     * @param id id del hotel
     * @param hotel datos actualizados
     * @return hotel actualizado
     */
    Hotel update(String id, Hotel hotel);
    
    /**
     * Elimina un hotel
     * 
     * @param id id del hotel
     */
    void deleteById(String id);
    
    /**
     * Busca hoteles por nombre
     * 
     * @param name nombre del hotel
     * @return lista de hoteles
     */
    List<Hotel> findByName(String name);
}
