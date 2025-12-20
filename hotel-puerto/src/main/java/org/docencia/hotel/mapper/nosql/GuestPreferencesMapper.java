package org.docencia.hotel.mapper.nosql;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;

@Mapper(componentModel = "spring")
public interface GuestPreferencesMapper {

    /**
     * Transforma de GuestPreferencesDocument a GuestPreferences
     * 
     * @param document GuestPreferencesDocument
     * @return GuestPreferences
     */
    @Mapping(source = "specialRequests", target = "specialRequest")  
    GuestPreferences toDomain(GuestPreferencesDocument document);

    /**
     * Transforma de GuestPreferences a GuestPreferencesDocument
     * 
     * @param domain GuestPreferences
     * @return GuestPreferencesDocument
     */
    @Mapping(source = "specialRequest", target = "specialRequests")  
    GuestPreferencesDocument toDocument(GuestPreferences domain);

    /**
     * Transforma una lista de GuestPreferencesDocument a una de GuestPreferences
     * 
     * @param documents lista de GuestPreferencesDocument
     * @return lista de GuestPreferences
     */
    List<GuestPreferences> toDomainList(List<GuestPreferencesDocument> documents);

    /**
     * Transforma una lista de GuestPreferences a una de GuestPreferencesDocument
     * 
     * @param domains lista de GuestPreferences
     * @return lista de GuestPreferencesDocument
     */
    List<GuestPreferencesDocument> toDocumentList(List<GuestPreferences> domains);
}
