package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.web.soap.RoomSoapService;

import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(
    endpointInterface = "org.docencia.hotel.web.soap.RoomSoapService",
    targetNamespace = "http://hotel.docencia.org/ws",
    serviceName = "RoomSoapService",
    portName = "RoomSoapPort"
)
public class RoomSoapServiceImpl implements RoomSoapService {

    private final RoomDomain roomDomain;

    public RoomSoapServiceImpl(RoomDomain roomDomain) {
        this.roomDomain = roomDomain;
    }

    @Override
    public Room getRoomById(String id) {
        return roomDomain.getRoom(id)
                .orElseThrow(() -> new RuntimeException("Room not found: " + id));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDomain.getAllRooms();
    }

    @Override
    public List<Room> getRoomsByHotel(String hotelId) {
        return roomDomain.getRoomsByHotel(hotelId);
    }

    @Override
    public List<Room> getRoomsByType(String type) {
        return roomDomain.getRoomsByType(type);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomDomain.createRoom(room);
    }

    @Override
    public Room updateRoom(String id, Room room) {
        return roomDomain.updateRoom(id, room);
    }

    @Override
    public void deleteRoom(String id) {
        roomDomain.deleteRoom(id);
    }
}
