package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.persistence.repository.jpa.BookingRepository;
import org.docencia.hotel.persistence.repository.jpa.GuestJpaRepository;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingServiceImplTest {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private GuestServiceImpl guestServiceImpl;

    @Autowired
    private HotelServiceImpl hotelServiceImpl;

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @Autowired
    private static BookingRepository bookingRepository;

    @Autowired
    private static RoomRepository roomRepository;

    @Autowired
    private static GuestJpaRepository guestRepository;

    @Autowired
    private static HotelRepository hotelRepository;

    private static Hotel hotel;
    private static Room room1;
    private static Room room2;
    private static Guest guest1;
    private static Guest guest2;
    private static Booking booking1;
    private static Booking booking2;

    @BeforeEach
    void setUp() {
        
        hotel = new Hotel();
        hotel.setName("Test Hotel");
        hotel.setAddress("Test Address");
        hotel = hotelServiceImpl.save(hotel);

        
        room1 = new Room();
        room1.setNumber(101);
        room1.setType("Single");
        room1.setPrice_per_night(100.0f);
        room1.setHotelId(hotel.getId());
        room1 = roomServiceImpl.save(room1);

        
        room2 = new Room();
        room2.setNumber(202);
        room2.setType("Double");
        room2.setPrice_per_night(150.0f);
        room2.setHotelId(hotel.getId());
        room2 = roomServiceImpl.save(room2);

        
        guest1 = new Guest();
        guest1.setFull_name("Juan Perez");
        guest1.setEmail("juan" + UUID.randomUUID() + "@test.com");
        guest1.setPhone("111222333");
        guest1 = guestServiceImpl.save(guest1);

        
        guest2 = new Guest();
        guest2.setFull_name("Maria Garcia");
        guest2.setEmail("maria" + UUID.randomUUID() + "@test.com");
        guest2.setPhone("444555666");
        guest2 = guestServiceImpl.save(guest2);

        
        booking1 = new Booking();
        booking1.setRoomId(room1.getId());
        booking1.setGuestId(guest1.getId());
        booking1.setCheck_in("2026-03-01");
        booking1.setCheck_out("2026-03-05");
        booking1 = bookingServiceImpl.save(booking1);

        
        booking2 = new Booking();
        booking2.setRoomId(room2.getId());
        booking2.setGuestId(guest2.getId());
        booking2.setCheck_in("2026-04-01");
        booking2.setCheck_out("2026-04-05");
        booking2 = bookingServiceImpl.save(booking2);
    }

    @AfterAll
    static void tearDown() {
        
        if (booking1 != null && booking1.getId() != null) {
            try {
                if (bookingRepository.existsById(booking1.getId())) {
                    bookingRepository.deleteById(booking1.getId());
                }
            } catch (Exception e) {
                
            }
        }

        
        if (booking2 != null && booking2.getId() != null) {
            try {
                if (bookingRepository.existsById(booking2.getId())) {
                    bookingRepository.deleteById(booking2.getId());
                }
            } catch (Exception e) {
                
            }
        }

        
        if (guest1 != null && guest1.getId() != null) {
            try {
                if (guestRepository.existsById(guest1.getId())) {
                    guestRepository.deleteById(guest1.getId());
                }
            } catch (Exception e) {
                
            }
        }

        if (guest2 != null && guest2.getId() != null) {
            try {
                if (guestRepository.existsById(guest2.getId())) {
                    guestRepository.deleteById(guest2.getId());
                }
            } catch (Exception e) {
                
            }
        }

        
        if (room1 != null && room1.getId() != null) {
            try {
                if (roomRepository.existsById(room1.getId())) {
                    roomRepository.deleteById(room1.getId());
                }
            } catch (Exception e) {
                
            }
        }

        if (room2 != null && room2.getId() != null) {
            try {
                if (roomRepository.existsById(room2.getId())) {
                    roomRepository.deleteById(room2.getId());
                }
            } catch (Exception e) {
                
            }
        }

        
        if (hotel != null && hotel.getId() != null) {
            try {
                if (hotelRepository.existsById(hotel.getId())) {
                    hotelRepository.deleteById(hotel.getId());
                }
            } catch (Exception e) {
                
            }
        }
    }

    @Test
    void testFindAll() {
        List<Booking> bookings = bookingServiceImpl.findAll();
        assertNotNull(bookings);
        assertTrue(bookings.size() >= 2);
    }

    @Test
    void testFindById() {
        Optional<Booking> result = bookingServiceImpl.findById(booking1.getId());
        assertTrue(result.isPresent());
        assertEquals(booking1.getId(), result.get().getId());
        assertEquals(room1.getId(), result.get().getRoomId());
        assertEquals(guest1.getId(), result.get().getGuestId());
    }

    @Test
    void testSave() {
        assertNotNull(booking1.getId());
        assertEquals(room1.getId(), booking1.getRoomId());
        assertEquals(guest1.getId(), booking1.getGuestId());
        assertEquals("2026-03-01", booking1.getCheck_in());
        assertEquals("2026-03-05", booking1.getCheck_out());
    }

    @Test
    void testUpdate() {
        Booking updated = new Booking();
        updated.setRoomId(room1.getId());
        updated.setGuestId(guest1.getId());
        updated.setCheck_in("2026-05-01");
        updated.setCheck_out("2026-05-10");

        Booking result = bookingServiceImpl.update(booking1.getId(), updated);

        assertEquals(booking1.getId(), result.getId());
        assertEquals("2026-05-01", result.getCheck_in());
        assertEquals("2026-05-10", result.getCheck_out());
    }

    @Test
    void testDeleteById() {
        String bookingId = booking2.getId();
        bookingServiceImpl.deleteById(bookingId);

        Optional<Booking> result = bookingServiceImpl.findById(bookingId);
        assertFalse(result.isPresent());

        booking2 = null;
    }

    @Test
    void testFindByGuestId() {
        List<Booking> bookings = bookingServiceImpl.findByGuestId(guest1.getId());

        assertNotNull(bookings);
        assertTrue(bookings.size() >= 1);
        assertEquals(guest1.getId(), bookings.get(0).getGuestId());
    }

    @Test
    void testFindByRoomId() {
        List<Booking> bookings = bookingServiceImpl.findByRoomId(room1.getId());

        assertNotNull(bookings);
        assertTrue(bookings.size() >= 1);
        assertEquals(room1.getId(), bookings.get(0).getRoomId());
    }
}
