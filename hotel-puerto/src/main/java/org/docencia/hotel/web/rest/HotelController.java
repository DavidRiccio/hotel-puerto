package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.HotelDomain;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.domain.model.Room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hotels", description = "Operaciones REST de hoteles")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelDomain hotelDomain;

    public HotelController(HotelDomain hotelDomain) {
        this.hotelDomain = hotelDomain;
    }

    @Operation(summary = "Obtener hotel por id")
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findById(@PathVariable String id) {
        return hotelDomain.getHotel(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los hoteles")
    @GetMapping
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.ok(hotelDomain.getAllHotels());
    }

    @Operation(summary = "Obtener habitaciones de un hotel")
    @GetMapping("/{id}/rooms")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable String id) {
        return ResponseEntity.ok(hotelDomain.getRoomsByHotel(id));
    }

    @Operation(summary = "Crear hotel")
    @PostMapping
    public ResponseEntity<Hotel> save(@Valid @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelDomain.createHotel(hotel));
    }

    @Operation(summary = "Actualizar hotel")
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(@PathVariable String id, @Valid @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelDomain.updateHotel(id, hotel));
    }

    @Operation(summary = "Eliminar hotel")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        hotelDomain.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
