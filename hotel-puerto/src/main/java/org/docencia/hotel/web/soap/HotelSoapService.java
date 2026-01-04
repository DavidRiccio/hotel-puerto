package org.docencia.hotel.web.soap;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(
    name = "Hotels",
    targetNamespace = "http://hotel.docencia.org/ws"
)
public interface HotelSoapService {

    @WebMethod(operationName = "FindById")
    @WebResult(name = "hotel")
    Hotel getHotelById(@WebParam(name = "id") String id);

    @WebMethod(operationName = "FindAllHotels")
    @WebResult(name = "hotels")
    List<Hotel> getAllHotels();

    @WebMethod(operationName = "GetRoomsByHotel")
    @WebResult(name = "rooms")
    List<Room> getRoomsByHotel(@WebParam(name = "hotelId") String hotelId);

    @WebMethod(operationName = "Save")
    @WebResult(name = "hotel")
    Hotel saveHotel(@WebParam(name = "hotel") Hotel hotel);

    @WebMethod(operationName = "Update")
    @WebResult(name = "hotel")
    Hotel updateHotel(
        @WebParam(name = "id") String id,
        @WebParam(name = "hotel") Hotel hotel
    );

    @WebMethod(operationName = "Delete")
    void deleteHotel(@WebParam(name = "id") String id);
}
