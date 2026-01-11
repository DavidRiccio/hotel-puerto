package org.docencia.hotel.web.soap.impl;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;
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

public class RoomSoapServiceImplTest {

    @Mock
    private RoomDomain roomDomain;

    @InjectMocks
    private RoomSoapServiceImpl roomSoapService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRoomById() {
        Room room = new Room();
        room.setId("R1");
        when(roomDomain.getRoom("R1")).thenReturn(Optional.of(room));

        Room result = roomSoapService.getRoomById("R1");

        assertNotNull(result);
        assertEquals("R1", result.getId());
        verify(roomDomain).getRoom("R1");
    }

    @Test
    void testGetRoomByIdNotFound() {
        when(roomDomain.getRoom("999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> roomSoapService.getRoomById("999"));
    }

    @Test
    void testGetAllRooms() {
        List<Room> rooms = Arrays.asList(new Room(), new Room());
        when(roomDomain.getAllRooms()).thenReturn(rooms);

        List<Room> result = roomSoapService.getAllRooms();

        assertEquals(2, result.size());
        verify(roomDomain).getAllRooms();
    }

    @Test
    void testGetRoomsByHotel() {
        List<Room> rooms = Arrays.asList(new Room());
        when(roomDomain.getRoomsByHotel("H1")).thenReturn(rooms);

        List<Room> result = roomSoapService.getRoomsByHotel("H1");

        assertFalse(result.isEmpty());
        verify(roomDomain).getRoomsByHotel("H1");
    }

    @Test
    void testGetRoomsByType() {
        List<Room> rooms = Arrays.asList(new Room());
        when(roomDomain.getRoomsByType("SUITE")).thenReturn(rooms);

        List<Room> result = roomSoapService.getRoomsByType("SUITE");

        assertFalse(result.isEmpty());
        verify(roomDomain).getRoomsByType("SUITE");
    }

    @Test
    void testSaveRoom() {
        Room room = new Room();
        when(roomDomain.createRoom(any(Room.class))).thenReturn(room);

        Room result = roomSoapService.saveRoom(room);

        assertNotNull(result);
        verify(roomDomain).createRoom(room);
    }

    @Test
    void testUpdateRoom() {
        Room room = new Room();
        when(roomDomain.updateRoom(eq("R1"), any(Room.class))).thenReturn(room);

        Room result = roomSoapService.updateRoom("R1", room);

        assertNotNull(result);
        verify(roomDomain).updateRoom("R1", room);
    }

    @Test
    void testDeleteRoom() {
        doNothing().when(roomDomain).deleteRoom("R1");

        roomSoapService.deleteRoom("R1");

        verify(roomDomain).deleteRoom("R1");
    }
}