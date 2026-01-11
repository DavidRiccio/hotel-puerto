package org.docencia.hotel.domain.impl;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.service.api.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomDomainImplTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomDomainImpl roomDomain;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRoom() {
        Room room = new Room();
        room.setId("R1");
        when(roomService.findById("R1")).thenReturn(Optional.of(room));

        Optional<Room> result = roomDomain.getRoom("R1");

        assertTrue(result.isPresent());
        assertEquals("R1", result.get().getId());
        verify(roomService, times(1)).findById("R1");
    }

    @Test
    void testGetAllRooms() {
        List<Room> rooms = Arrays.asList(new Room(), new Room());
        when(roomService.findAll()).thenReturn(rooms);

        List<Room> result = roomDomain.getAllRooms();

        assertEquals(2, result.size());
        verify(roomService).findAll();
    }

    @Test
    void testGetRoomsByHotel() {
        List<Room> rooms = Arrays.asList(new Room());
        when(roomService.findByHotelId("H1")).thenReturn(rooms);

        List<Room> result = roomDomain.getRoomsByHotel("H1");

        assertFalse(result.isEmpty());
        verify(roomService).findByHotelId("H1");
    }

    @Test
    void testGetRoomsByType() {
        List<Room> rooms = Arrays.asList(new Room());
        when(roomService.findByType("DOUBLE")).thenReturn(rooms);

        List<Room> result = roomDomain.getRoomsByType("DOUBLE");

        assertFalse(result.isEmpty());
        verify(roomService).findByType("DOUBLE");
    }

    @Test
    void testCreateRoom() {
        Room room = new Room();
        when(roomService.save(any(Room.class))).thenReturn(room);

        Room result = roomDomain.createRoom(room);

        assertNotNull(result);
        verify(roomService).save(room);
    }

    @Test
    void testUpdateRoom() {
        Room room = new Room();
        when(roomService.update(eq("R1"), any(Room.class))).thenReturn(room);

        Room result = roomDomain.updateRoom("R1", room);

        assertNotNull(result);
        verify(roomService).update("R1", room);
    }

    @Test
    void testDeleteRoom() {

        doNothing().when(roomService).deleteById("R1");

        roomDomain.deleteRoom("R1");

        verify(roomService).deleteById("R1");
    }
}