package org.docencia.hotel.persistence.repository.jpa;

import java.util.List;

import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, String> {
    /**
     * Busca las habitaciones de un hotel
     * 
     * @param hotelId id del hotel
     * @return Lista con las habitaciones del hotel
     */
    List<RoomEntity> findByHotelId(String hotelId);

    /**
     * Busca las habitaciones de un tipo
     * 
     * @param type tipo de la habitacion
     * @return Lista con las habitaciones de ese tipo
     */
    List<RoomEntity> findByType(String type);
}
