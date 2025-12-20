package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.mapper.jpa.RoomMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.docencia.hotel.service.api.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomMapper roomMapper;

    @Override
    public List<Room> findAll() {
        return roomMapper.toDomainList(roomRepository.findAll());
    }

    @Override
    public Optional<Room> findById(String id) {
        return roomRepository.findById(id).map(roomMapper::toDomain);
    }

    @Override
    public Room save(Room room) {
        RoomEntity entity = roomMapper.toEntity(room);
        HotelEntity hotel = hotelRepository.findById(room.getHotelId()).orElse(null);
        entity.setHotel(hotel);
        RoomEntity saved = roomRepository.save(entity);
        return roomMapper.toDomain(saved);
    }

    @Override
    public Room update(String id, Room room) {
        RoomEntity newRoom = roomRepository.findById(id).orElse(null);
        HotelEntity hotel = hotelRepository.findById(room.getHotelId()).orElse(null);
        newRoom.setHotel(hotel);
        newRoom.setNumber(room.getNumber());
        newRoom.setPrice_per_night(room.getPrice_per_night());
        newRoom.setType(room.getType());
        roomRepository.save(newRoom);
        return roomMapper.toDomain(newRoom);

    }

    @Override
    public void deleteById(String id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> findByHotelId(String hotelId) {
        List<RoomEntity> rooms = roomRepository.findByHotelId(hotelId);
        return roomMapper.toDomainList(rooms);
    }

    @Override
    public List<Room> findByType(String type) {
        List<RoomEntity> rooms = roomRepository.findByType(type);
        return roomMapper.toDomainList(rooms);
    }
}
