package org.docencia.hotel.domain.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.HotelService;
import org.docencia.hotel.service.api.RoomService;
import org.springframework.stereotype.Service;

@Service
public class HotelDomainImpl implements HotelDomain {

    private final HotelService hotelService;
    private final RoomService roomService;

    public HotelDomainImpl(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @Override
    public Optional<Hotel> getHotel(String id) {
        return hotelService.findById(id);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelService.findAll();
    }

    @Override
    public List<Room> getRoomsByHotel(String hotelId) {
        return roomService.findByHotelId(hotelId);
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelService.save(hotel);
    }

    @Override
    public Hotel updateHotel(String id, Hotel hotel) {
        return hotelService.update(id, hotel);
    }

    @Override
    public void deleteHotel(String id) {
        hotelService.deleteById(id);
    }
}
