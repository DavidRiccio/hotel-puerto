package org.docencia.hotel.web.soap;

import org.docencia.hotel.domain.model.Guest;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(
    name = "Guests",
    targetNamespace = "http://hotel.docencia.org/ws"
)
public interface GuestSoapService {

    @WebMethod(operationName = "FindById")
    @WebResult(name = "guest")
    Guest getGuestById(@WebParam(name = "id") String id);

    @WebMethod(operationName = "FindAll")
    @WebResult(name = "guests")
    List<Guest> getAllGuests();

    @WebMethod(operationName = "Save")
    @WebResult(name = "guest")
    Guest saveGuest(@WebParam(name = "guest") Guest guest);

    @WebMethod(operationName = "Update")
    @WebResult(name = "guest")
    Guest updateGuest(
        @WebParam(name = "id") String id,
        @WebParam(name = "guest") Guest guest
    );

    @WebMethod(operationName = "Delete")
    void deleteGuest(@WebParam(name = "id") String id);
}
