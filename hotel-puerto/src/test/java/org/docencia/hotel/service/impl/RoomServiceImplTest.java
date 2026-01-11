package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.docencia.hotel.persistence.repository.jpa.RoomRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoomServiceImplTest {

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @Autowired
    private HotelServiceImpl hotelServiceImpl;

    @Autowired
    private static RoomRepository roomRepository;

    @Autowired
    private static HotelRepository hotelRepository;

    private static Hotel hotel;
    private static Room room1;
    private static Room room2;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.setName("Test Hotel");
        hotel.setAddress("Test Address 123");
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
    }

    @AfterAll
    static void tearDown() {
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
        List<Room> rooms = roomServiceImpl.findAll();
        assertNotNull(rooms);
        assertTrue(rooms.size() >= 2);
    }

    @Test
    void testFindById() {
        Optional<Room> result = roomServiceImpl.findById(room1.getId());
        assertTrue(result.isPresent());
        assertEquals(101, result.get().getNumber());
        assertEquals("Single", result.get().getType());
    }

    @Test
    void testSave() {
        assertNotNull(room1.getId());
        assertEquals(101, room1.getNumber());
        assertEquals(100.0f, room1.getPrice_per_night());
    }

    @Test
    void testUpdate() {
        Room updated = new Room();
        updated.setNumber(103);
        updated.setType("Single Deluxe");
        updated.setPrice_per_night(120.0f);
        updated.setHotelId(hotel.getId());

        Room result = roomServiceImpl.update(room1.getId(), updated);

        assertEquals(room1.getId(), result.getId());
        assertEquals(103, result.getNumber());
        assertEquals("Single Deluxe", result.getType());
        assertEquals(120.0f, result.getPrice_per_night());
    }

    @Test
    void testDeleteById() {
        String roomId = room2.getId();
        roomServiceImpl.deleteById(roomId);

        Optional<Room> result = roomServiceImpl.findById(roomId);
        assertFalse(result.isPresent());

        room2 = null;
    }

    @Test
    void testFindByHotelId() {
        List<Room> rooms = roomServiceImpl.findByHotelId(hotel.getId());
        
        assertNotNull(rooms);
        assertTrue(rooms.size() >= 2);
        assertEquals(hotel.getId(), rooms.get(0).getHotelId());
    }

    @Test
    void testFindByType() {
        List<Room> singleRooms = roomServiceImpl.findByType("Single");
        
        assertNotNull(singleRooms);
        assertTrue(singleRooms.size() >= 1);
        assertEquals("Single", singleRooms.get(0).getType());
    }
}
