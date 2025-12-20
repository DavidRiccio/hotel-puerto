package org.docencia.hotel.persistence.repository.nosql;

import java.util.Optional;

import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuestPreferencesRepository extends MongoRepository<GuestPreferencesDocument, String> {

    /**
     * Busca las preferencias de un huésped por su guestId
     * 
     * @param guestId id del huésped en H2
     * @return Preferencias del huésped o vacío
     */
    Optional<GuestPreferencesDocument> findByGuestId(String guestId);

    /**
     * Elimina las preferencias de un huésped
     * 
     * @param guestId id del huésped
     */
    void deleteByGuestId(String guestId);
}
