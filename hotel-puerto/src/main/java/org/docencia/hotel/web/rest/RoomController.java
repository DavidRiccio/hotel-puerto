package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.RoomDomain;
import org.docencia.hotel.domain.model.Room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Rooms",
    description = "Operaciones REST de habitaciones"
)
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomDomain roomDomain;

    public RoomController(RoomDomain roomDomain) {
        this.roomDomain = roomDomain;
    }

    @Operation(
        summary = "Obtener habitacion por id",
        description = "Devuelve una habitacion existente a partir de su identificador"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Habitacion encontrada",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Room.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Habitacion no encontrada",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable String id) {
        return roomDomain.getRoom(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Listar todas las habitaciones",
        description = "Devuelve la lista completa de habitaciones registradas"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de habitaciones",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Room.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok(roomDomain.getAllRooms());
    }

    @Operation(
        summary = "Obtener habitaciones por hotel",
        description = "Devuelve todas las habitaciones que pertenecen a un hotel especifico"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de habitaciones del hotel",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Room.class)
            )
        )
    })
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> findByHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(roomDomain.getRoomsByHotel(hotelId));
    }

    @Operation(
        summary = "Obtener habitaciones por tipo",
        description = "Devuelve todas las habitaciones filtradas por tipo (suite, doble, individual, etc.)"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de habitaciones del tipo especificado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Room.class)
            )
        )
    })
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Room>> findByType(@PathVariable String type) {
        return ResponseEntity.ok(roomDomain.getRoomsByType(type));
    }

    @Operation(
        summary = "Crear una nueva habitacion",
        description = "Crea una nueva habitacion a partir de los datos enviados en el cuerpo de la peticion"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Habitacion creada correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Room.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Room> save(@Valid @RequestBody Room room) {
        return ResponseEntity.ok(roomDomain.createRoom(room));
    }

    @Operation(
        summary = "Actualizar una habitacion existente",
        description = "Actualiza los datos de una habitacion identificada por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Habitacion actualizada correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Room.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable String id, @Valid @RequestBody Room room) {
        return ResponseEntity.ok(roomDomain.updateRoom(id, room));
    }

    @Operation(
        summary = "Eliminar una habitacion",
        description = "Elimina una habitacion existente identificada por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Habitacion eliminada correctamente",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roomDomain.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
