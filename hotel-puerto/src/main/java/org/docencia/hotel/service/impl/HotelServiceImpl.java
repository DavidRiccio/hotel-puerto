package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.mapper.jpa.HotelMapper;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.service.api.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired 
    HotelMapper hotelMapper;

    @Override
    public List<Hotel> findAll() {
        List<HotelEntity> hotels = hotelRepository.findAll();
        return hotelMapper.toDomainList(hotels);
    }

    @Override
    public Optional<Hotel> findById(String id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toDomain);
    }

    @Override
    public Hotel save(Hotel hotel) {
        HotelEntity entity = hotelMapper.toEntity(hotel);
        HotelEntity saved = hotelRepository.save(entity);
        return hotelMapper.toDomain(saved);
    }

    @Override
    public Hotel update(String id, Hotel hotel) {
        HotelEntity hotelEntity = hotelRepository.findById(id).orElse(null);
        hotelEntity.setAddress(hotel.getAddress());
        hotelEntity.setName(hotel.getName());
        hotelRepository.save(hotelEntity);
        return hotelMapper.toDomain(hotelEntity);
    }

    @Override
    public void deleteById(String id) {
        HotelEntity hotelEntity = hotelRepository.findById(id).orElse(null);
        hotelRepository.delete(hotelEntity);
    }

    @Override
    public List<Hotel> findByName(String name) {
        List<HotelEntity> hotels = hotelRepository.findByName(name);
        return hotelMapper.toDomainList(hotels);
    }
}
