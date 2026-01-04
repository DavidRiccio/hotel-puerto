package org.docencia.hotel.web.soap;

import org.docencia.hotel.domain.model.Room;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(
    name = "Rooms",
    targetNamespace = "http://hotel.docencia.org/ws"
)
public interface RoomSoapService {

    @WebMethod(operationName = "FindById")
    @WebResult(name = "room")
    Room getRoomById(@WebParam(name = "id") String id);

    @WebMethod(operationName = "FindAllRooms")
    @WebResult(name = "rooms")
    List<Room> getAllRooms();

    @WebMethod(operationName = "GetRoomsByHotel")
    @WebResult(name = "rooms")
    List<Room> getRoomsByHotel(@WebParam(name = "hotelId") String hotelId);

    @WebMethod(operationName = "GetRoomsByType")
    @WebResult(name = "rooms")
    List<Room> getRoomsByType(@WebParam(name = "type") String type);

    @WebMethod(operationName = "Save")
    @WebResult(name = "room")
    Room saveRoom(@WebParam(name = "room") Room room);

    @WebMethod(operationName = "Update")
    @WebResult(name = "room")
    Room updateRoom(
        @WebParam(name = "id") String id,
        @WebParam(name = "room") Room room
    );

    @WebMethod(operationName = "Delete")
    void deleteRoom(@WebParam(name = "id") String id);
}
