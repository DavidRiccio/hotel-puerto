package org.docencia.hotel.mapper.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    /**
     * Transforma de RoomEntity a Room
     * 
     * @param entity RoomEntity
     * @return Room
     */
    @Mapping(source = "hotel.id", target = "hotelId")
    Room toDomain(RoomEntity entity);

    /**
     * Transforma de Room a RoomEntity
     * 
     * @param domain Room
     * @return RoomEntity
     */
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(target = "bookings", ignore = true)  
    RoomEntity toEntity(Room domain);

    /**
     * Transforma una lista de RoomEntity a una de Room
     * 
     * @param entities lista de RoomEntity
     * @return lista de Room
     */
    List<Room> toDomainList(List<RoomEntity> entities);

    /**
     * Transforma una lista de Room a una de RoomEntity
     * 
     * @param domains lista de Room
     * @return lista de RoomEntity
     */
    List<RoomEntity> toEntityList(List<Room> domains);
}
