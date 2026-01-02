package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Rooms", description = "Operaciones REST de habitaciones")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomDomain roomDomain;

    public RoomController(RoomDomain roomDomain) {
        this.roomDomain = roomDomain;
    }

    @Operation(summary = "Obtener habitacion por id")
    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable String id) {
        return roomDomain.getRoom(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las habitaciones")
    @GetMapping
    public ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok(roomDomain.getAllRooms());
    }

    @Operation(summary = "Obtener habitaciones por hotel")
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> findByHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(roomDomain.getRoomsByHotel(hotelId));
    }

    @Operation(summary = "Obtener habitaciones por tipo")
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Room>> findByType(@PathVariable String type) {
        return ResponseEntity.ok(roomDomain.getRoomsByType(type));
    }

    @Operation(summary = "Crear habitacion")
    @PostMapping
    public ResponseEntity<Room> save(@Valid @RequestBody Room room) {
        return ResponseEntity.ok(roomDomain.createRoom(room));
    }

    @Operation(summary = "Actualizar habitacion")
    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable String id, @Valid @RequestBody Room room) {
        return ResponseEntity.ok(roomDomain.updateRoom(id, room));
    }

    @Operation(summary = "Eliminar habitacion")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roomDomain.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
