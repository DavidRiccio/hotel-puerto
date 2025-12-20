package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.mapper.jpa.GuestMapper;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.service.api.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestJpaRepository guestJpaRepository;

    @Autowired
    GuestMapper guestMapper;

    @Override
    public List<Guest> findAll() {
        return guestMapper.toDomainList(guestJpaRepository.findAll());
    }

    @Override
    public Optional<Guest> findById(String id) {
        return guestJpaRepository.findById(id)
                .map(guestMapper::toDomain);
    }

    @Override
    public Guest save(Guest guest) {
        GuestEntity entity = guestMapper.toEntity(guest);
        GuestEntity saved = guestJpaRepository.save(entity);
        return guestMapper.toDomain(saved);
    }

    @Override
    public Guest update(String id, Guest guest) {
        GuestEntity guest2 = guestJpaRepository.findById(id).orElse(null);
        guest2.setEmail(guest.getEmail());
        guest2.setFull_name(guest.getFull_name());
        guest2.setPhone(guest.getPhone());
        guest2.setPreferencesId(guest.getPreferencesId());
        guestJpaRepository.save(guest2);
        return guestMapper.toDomain(guest2);
    }

    @Override
    public void deleteById(String id) {
        guestJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Guest> findByEmail(String email) {
        return guestJpaRepository.findByEmail(email).map(guestMapper :: toDomain);
    }

    @Override
    public GuestPreferences savePreferences(String guestId, GuestPreferences preferences) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePreferences'");
    }

    @Override
    public Optional<GuestPreferences> findPreferencesByGuestId(String guestId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPreferencesByGuestId'");
    }
}
