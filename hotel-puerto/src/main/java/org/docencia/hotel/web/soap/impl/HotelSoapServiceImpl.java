package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.web.soap.HotelSoapService;

import jakarta.jws.WebService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(
    endpointInterface = "org.docencia.hotel.web.soap.HotelSoapService",
    targetNamespace = "http://hotel.docencia.org/ws",
    serviceName = "HotelSoapService",
    portName = "HotelSoapPort"
)
public class HotelSoapServiceImpl implements HotelSoapService {

    private final HotelDomain hotelDomain;

    public HotelSoapServiceImpl(HotelDomain hotelDomain) {
        this.hotelDomain = hotelDomain;
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelDomain.getHotel(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found: " + id));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDomain.getAllHotels();
    }

    @Override
    public List<Room> getRoomsByHotel(String hotelId) {
        return hotelDomain.getRoomsByHotel(hotelId);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelDomain.createHotel(hotel);
    }

    @Override
    public Hotel updateHotel(String id, Hotel hotel) {
        return hotelDomain.updateHotel(id, hotel);
    }

    @Override
    public void deleteHotel(String id) {
        hotelDomain.deleteHotel(id);
    }
}
