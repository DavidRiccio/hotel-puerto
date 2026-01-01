package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.GuestDomain;
import org.docencia.hotel.domain.model.Guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Guests", description = "Operaciones REST de huespedes")
@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestDomain guestDomain;

    public GuestController(GuestDomain guestDomain) {
        this.guestDomain = guestDomain;
    }

    @Operation(summary = "Obtener huesped por id")
    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable String id) {
        return guestDomain.getGuest(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los huespedes")
    @GetMapping
    public ResponseEntity<List<Guest>> findAll() {
        return ResponseEntity.ok(guestDomain.getAllGuests());
    }

    @Operation(summary = "Guardar huesped")
    @PostMapping
    public ResponseEntity<Guest> save(@Valid @RequestBody Guest guest) {
        return ResponseEntity.ok(guestDomain.createGuest(guest));
    }

    @Operation(summary = "Actualizar huesped")
    @PutMapping("/{id}")
    public ResponseEntity<Guest> update(@PathVariable String id, @Valid @RequestBody Guest guest) {
        return ResponseEntity.ok(guestDomain.updateGuest(id, guest));
    }

    @Operation(summary = "Eliminar huesped")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        guestDomain.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}
