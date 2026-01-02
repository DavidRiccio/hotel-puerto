package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
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
    name = "Hotels",
    description = "Operaciones REST de hoteles"
)
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelDomain hotelDomain;

    public HotelController(HotelDomain hotelDomain) {
        this.hotelDomain = hotelDomain;
    }

    @Operation(
        summary = "Obtener hotel por id",
        description = "Devuelve un hotel existente a partir de su identificador"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Hotel encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Hotel.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Hotel no encontrado",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable String id) {
        return hotelDomain.getHotel(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Listar todos los hoteles",
        description = "Devuelve la lista completa de hoteles registrados"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de hoteles",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Hotel.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.ok(hotelDomain.getAllHotels());
    }

    @Operation(
        summary = "Obtener habitaciones de un hotel",
        description = "Devuelve todas las habitaciones asociadas a un hotel especifico"
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
    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable String id) {
        return ResponseEntity.ok(hotelDomain.getRoomsByHotel(id));
    }

    @Operation(
        summary = "Crear un nuevo hotel",
        description = "Crea un nuevo hotel a partir de los datos enviados en el cuerpo de la peticion"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Hotel creado correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Hotel.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Hotel> save(@Valid @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelDomain.createHotel(hotel));
    }

    @Operation(
        summary = "Actualizar un hotel existente",
        description = "Actualiza los datos de un hotel identificado por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Hotel actualizado correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Hotel.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable String id, @Valid @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelDomain.updateHotel(id, hotel));
    }

    @Operation(
        summary = "Eliminar un hotel",
        description = "Elimina un hotel existente identificado por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Hotel eliminado correctamente",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hotelDomain.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
