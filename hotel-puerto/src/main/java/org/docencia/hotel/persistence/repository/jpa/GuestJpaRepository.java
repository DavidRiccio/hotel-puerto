package org.docencia.hotel.persistence.repository.jpa;

import java.util.Optional;

import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestJpaRepository extends JpaRepository<GuestEntity, String> {
    /**
     * Busca un huesped por email
     * 
     * @param email email del huesped
     * @return El huesped o null
     */
    Optional<GuestEntity> findByEmail(String email);

}
