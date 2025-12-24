package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.mapper.jpa.GuestMapper;
import org.docencia.hotel.mapper.nosql.GuestPreferencesMapper;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.nosql.GuestPreferencesRepository;
import org.docencia.hotel.service.api.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestJpaRepository guestJpaRepository;

    @Autowired
    private GuestPreferencesRepository guestPreferencesRepository;

    @Autowired
    private GuestMapper guestMapper;

    @Autowired
    private GuestPreferencesMapper guestPreferencesMapper;

    @Override
    public List<Guest> findAll() {
        return guestMapper.toDomainList(guestJpaRepository.findAll());
    }

    @Override
    public Optional<Guest> findById(String id) {
        return guestJpaRepository.findById(id).map(guestMapper::toDomain);
    }

    @Override
    public Guest save(Guest guest) {
        GuestEntity entity = guestMapper.toEntity(guest);
        GuestEntity saved = guestJpaRepository.save(entity);
        return guestMapper.toDomain(saved);
    }

    @Override
    public Guest update(String id, Guest guest) {
        GuestEntity guestEntity = guestJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        guestEntity.setFull_name(guest.getFull_name());
        guestEntity.setEmail(guest.getEmail());
        guestEntity.setPhone(guest.getPhone());
        guestEntity.setPreferencesId(guest.getPreferencesId());

        GuestEntity updated = guestJpaRepository.save(guestEntity);
        return guestMapper.toDomain(updated);
    }

    @Override
    public void deleteById(String id) {
        guestJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Guest> findByEmail(String email) {
        return guestJpaRepository.findByEmail(email).map(guestMapper::toDomain);
    }

    @Override
    public GuestPreferences savePreferences(String guestId, GuestPreferences preferences) {

        if (!guestJpaRepository.existsById(guestId)) {
            throw new RuntimeException("Guest not found");
        }

        preferences.setGuestId(guestId);

        GuestPreferencesDocument document = guestPreferencesMapper.toDocument(preferences);
        GuestPreferencesDocument saved = guestPreferencesRepository.save(document);

        GuestEntity guestEntity = guestJpaRepository.findById(guestId).orElseThrow(() -> new RuntimeException("Guest not found"));
        guestEntity.setPreferencesId(saved.getId());
        guestJpaRepository.save(guestEntity);

        return guestPreferencesMapper.toDomain(saved);
    }

    @Override
    public Optional<GuestPreferences> findPreferencesByGuestId(String guestId) {
        return guestPreferencesRepository.findByGuestId(guestId).map(guestPreferencesMapper::toDomain);
    }
}
