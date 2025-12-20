package org.docencia.hotel.persistence.repository.jpa;

import java.util.List;

import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    /**
     * Busca las reservas a traves de la id del huesped
     * @param guestId id del huesped
     * @return Lista de reservas de ese huesped
     */
    List<BookingEntity> findByGuestId(String guestId);

    /**
     * Busca las reservas de una habitacion
     * @param roomId id de la habitacion
     * @return Lista con las reservas de esa habitacion
     */
    List<BookingEntity> findByRoomId(String roomId);

}
