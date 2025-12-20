package org.docencia.hotel.mapper.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    /**
     * Transforma de GuestEntity a Guest
     * 
     * @param entity GuestEntity
     * @return Guest
     */
    Guest toDomain(GuestEntity entity);

    /**
     * Transforma de Guest a GuestEntity
     * 
     * @param domain Guest
     * @return GuestEntity
     */
    @Mapping(target = "bookings", ignore = true)  
    GuestEntity toEntity(Guest domain);

    /**
     * Transforma una lista de GuestEntity a una de Guest
     * 
     * @param entities lista de GuestEntity
     * @return lista de Guest
     */
    List<Guest> toDomainList(List<GuestEntity> entities);

    /**
     * Transforma una lista de Guest a una de GuestEntity
     * 
     * @param domains lista de Guest
     * @return lista de GuestEntity
     */
    List<GuestEntity> toEntityList(List<Guest> domains);
}
