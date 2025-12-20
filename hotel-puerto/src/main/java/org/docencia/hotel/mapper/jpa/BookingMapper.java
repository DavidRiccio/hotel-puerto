package org.docencia.hotel.mapper.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    /**
     * Transforma de BookingEntity a Booking
     * 
     * @param entity BookingEntity
     * @return Booking
     */
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "guest.id", target = "guestId")
    Booking toDomain(BookingEntity entity);

    /**
     * Transforma de Booking a BookingEntity
     * 
     * @param domain Booking
     * @return BookingEntity
     */
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "guestId", target = "guest.id")
    BookingEntity toEntity(Booking domain);

    /**
     * Transforma una lista de BookingEntity a una de Booking
     * 
     * @param entities lista de BookingEntity
     * @return lista de Booking
     */
    List<Booking> toDomainList(List<BookingEntity> entities);

    /**
     * Transforma una lista de Booking a una de BookingEntity
     * 
     * @param domains lista de Booking
     * @return lista de BookingEntity
     */
    List<BookingEntity> toEntityList(List<Booking> domains);
}
