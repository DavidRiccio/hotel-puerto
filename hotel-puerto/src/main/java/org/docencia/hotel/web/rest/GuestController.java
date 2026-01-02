package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;

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
    name = "Guests",
    description = "Operaciones REST de huespedes"
)
@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestDomain guestDomain;

    public GuestController(GuestDomain guestDomain) {
        this.guestDomain = guestDomain;
    }

    @Operation(
        summary = "Obtener huesped por id",
        description = "Devuelve un huesped existente a partir de su identificador"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Huesped encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Guest.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Huesped no encontrado",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable String id) {
        return guestDomain.getGuest(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Listar todos los huespedes",
        description = "Devuelve la lista completa de huespedes registrados"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de huespedes",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Guest.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<Guest>> findAll() {
        return ResponseEntity.ok(guestDomain.getAllGuests());
    }

    @Operation(
        summary = "Crear un nuevo huesped",
        description = "Crea un nuevo huesped a partir de los datos enviados en el cuerpo de la peticion"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Huesped creado correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Guest.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Guest> save(@Valid @RequestBody Guest guest) {
        return ResponseEntity.ok(guestDomain.createGuest(guest));
    }

    @Operation(
        summary = "Actualizar un huesped existente",
        description = "Actualiza los datos de un huesped identificado por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Huesped actualizado correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Guest.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Guest> update(@PathVariable String id, @Valid @RequestBody Guest guest) {
        return ResponseEntity.ok(guestDomain.updateGuest(id, guest));
    }

    @Operation(
        summary = "Eliminar un huesped",
        description = "Elimina un huesped existente identificado por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Huesped eliminado correctamente",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        guestDomain.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}
