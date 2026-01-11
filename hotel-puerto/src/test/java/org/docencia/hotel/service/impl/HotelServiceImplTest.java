package org.docencia.hotel.service.impl;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.persistence.repository.jpa.HotelRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HotelServiceImplTest {

    @Autowired
    private HotelServiceImpl hotelServiceImpl;

    @Autowired
    private static HotelRepository hotelRepository;

    private static Hotel hotel1;
    private static Hotel hotel2;

    @BeforeEach
    void setUp() {
        // Crear hotel 1
        hotel1 = new Hotel();
        hotel1.setName("Hotel Plaza");
        hotel1.setAddress("Calle Mayor 123");
        hotel1 = hotelServiceImpl.save(hotel1);

        // Crear hotel 2
        hotel2 = new Hotel();
        hotel2.setName("Hotel Miramar");
        hotel2.setAddress("Avenida del Mar 456");
        hotel2 = hotelServiceImpl.save(hotel2);
    }

    @AfterAll
    static void tearDown() {
        // Eliminar hotel 1
        if (hotel1 != null && hotel1.getId() != null) {
            try {
                if (hotelRepository.existsById(hotel1.getId())) {
                    hotelRepository.deleteById(hotel1.getId());
                }
            } catch (Exception e) {
                // Ignorar
            }
        }

        // Eliminar hotel 2
        if (hotel2 != null && hotel2.getId() != null) {
            try {
                if (hotelRepository.existsById(hotel2.getId())) {
                    hotelRepository.deleteById(hotel2.getId());
                }
            } catch (Exception e) {
                // Ignorar
            }
        }
    }

    @Test
    void testFindAll() {
        List<Hotel> hotels = hotelServiceImpl.findAll();
        assertNotNull(hotels);
        assertTrue(hotels.size() >= 2);
    }

    @Test
    void testFindById() {
        Optional<Hotel> result = hotelServiceImpl.findById(hotel1.getId());
        assertTrue(result.isPresent());
        assertEquals("Hotel Plaza", result.get().getName());
    }

    @Test
    void testFindByName() {
        List<Hotel> hotels = hotelServiceImpl.findByName("Hotel Plaza");

        assertNotNull(hotels);
        assertTrue(hotels.size() >= 1);
        assertEquals("Hotel Plaza", hotels.get(0).getName());
    }

    @Test
    void testSave() {
        assertNotNull(hotel1.getId());
        assertEquals("Hotel Plaza", hotel1.getName());
        assertEquals("Calle Mayor 123", hotel1.getAddress());
    }

    @Test
    void testUpdate() {
        Hotel updated = new Hotel();
        updated.setName("Hotel Plaza Premium");
        updated.setAddress("Calle Mayor 123 - Renovado");

        Hotel result = hotelServiceImpl.update(hotel1.getId(), updated);

        assertEquals(hotel1.getId(), result.getId());
        assertEquals("Hotel Plaza Premium", result.getName());
        assertEquals("Calle Mayor 123 - Renovado", result.getAddress());
    }

    @Test
    void testDeleteById() {
        String hotelId = hotel2.getId();
        hotelServiceImpl.deleteById(hotelId);

        Optional<Hotel> result = hotelServiceImpl.findById(hotelId);
        assertFalse(result.isPresent());

        hotel2 = null;
    }
}
