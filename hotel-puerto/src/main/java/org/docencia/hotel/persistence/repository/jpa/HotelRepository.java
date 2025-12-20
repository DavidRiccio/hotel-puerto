package org.docencia.hotel.persistence.repository.jpa;

import java.util.List;

import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, String> {
    /**
     * Busca hoteles por nombre
     * 
     * @param name nombre del hotel
     * @return Lista con los hoteles de ese nombre
     */
    List<HotelEntity> findByName(String name);

    /**
     * Busca hoteles por direccion
     * 
     * @param address direccion del hotel
     * @return Lista con los hoteles con esa direccion
     */
    List<HotelEntity> findByAddress(String address);

}
