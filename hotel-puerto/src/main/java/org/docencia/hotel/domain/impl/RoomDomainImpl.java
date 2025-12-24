package org.docencia.hotel.domain.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomDomainImpl implements RoomDomain {

    private final RoomService roomService;

    public RoomDomainImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public Optional<Room> getRoom(String id) {
        return roomService.findById(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @Override
    public List<Room> getRoomsByHotel(String hotelId) {
        return roomService.findByHotelId(hotelId);
    }

    @Override
    public List<Room> getRoomsByType(String type) {
        return roomService.findByType(type);
    }

    @Override
    public Room createRoom(Room room) {
        return roomService.save(room);
    }

    @Override
    public Room updateRoom(String id, Room room) {
        return roomService.update(id, room);
    }

    @Override
    public void deleteRoom(String id) {
        roomService.deleteById(id);
    }
}
