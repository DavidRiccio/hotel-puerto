package org.docencia.hotel.mapper.jpa;

import org.mapstruct.Mapper;

import java.util.List;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    /**
     * Transforma de HotelDomain a HotelEntity
     * 
     * @param domain HotelDomain
     * @return HotelEntity
     */
    HotelEntity toEntity(Hotel domain);

    /**
     * Transforma de HotelEntity a HotelDomain
     * 
     * @param entity HotelEntity
     * @return HotelDomain
     */
    Hotel toDomain(HotelEntity entity);

    /**
     * Transforma una lista de HotelDomain a una de HotelEntity
     * 
     * @param domains lista de HotelDomain
     * @return lista de HotelEntity
     */
    List<HotelEntity> toEntityList(List<Hotel> domains);

    /**
     * Transforma una lista de HotelEntity a una de HotelDomain
     * 
     * @param domains lista de HotelEntity
     * @return lista de HotelDomain
     */
    List<Hotel> toDomainList(List<HotelEntity> entities);

}
