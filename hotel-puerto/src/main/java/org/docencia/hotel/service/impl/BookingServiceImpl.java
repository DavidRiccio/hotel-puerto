package org.docencia.hotel.service.impl;

import java.util.List;
import java.util.Optional;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.mapper.jpa.BookingMapper;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.repository.jpa.BookingRepository;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.docencia.hotel.service.api.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestJpaRepository guestRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public List<Booking> findAll() {
        return bookingMapper.toDomainList(bookingRepository.findAll());
    }

    @Override
    public Optional<Booking> findById(String id) {
        return bookingRepository.findById(id).map(bookingMapper::toDomain);
    }

    @Override
    public Booking save(Booking booking) {
        BookingEntity entity = bookingMapper.toEntity(booking);
        if (booking.getRoomId() != null) {
            RoomEntity room = roomRepository.findById(booking.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
            entity.setRoom(room);
        }

        if (booking.getGuestId() != null) {
            GuestEntity guest = guestRepository.findById(booking.getGuestId())
                    .orElseThrow(() -> new RuntimeException("Guest not found"));
            entity.setGuest(guest);
        }

        BookingEntity saved = bookingRepository.save(entity);
        return bookingMapper.toDomain(saved);
    }

    @Override
    public Booking update(String id, Booking booking) {
        BookingEntity bookingEntity = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingEntity.setCheck_in(booking.getCheck_in());
        bookingEntity.setCheck_out(booking.getCheck_out());

        if (booking.getRoomId() != null) {
            RoomEntity room = roomRepository.findById(booking.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
            bookingEntity.setRoom(room);
        }

        if (booking.getGuestId() != null) {
            GuestEntity guest = guestRepository.findById(booking.getGuestId())
                    .orElseThrow(() -> new RuntimeException("Guest not found"));
            bookingEntity.setGuest(guest);
        }

        BookingEntity updated = bookingRepository.save(bookingEntity);
        return bookingMapper.toDomain(updated);
    }

    @Override
    public void deleteById(String id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findByGuestId(String guestId) {
        return bookingMapper.toDomainList(bookingRepository.findByGuestId(guestId));
    }

    @Override
    public List<Booking> findByRoomId(String roomId) {
        return bookingMapper.toDomainList(bookingRepository.findByRoomId(roomId));
    }
}
