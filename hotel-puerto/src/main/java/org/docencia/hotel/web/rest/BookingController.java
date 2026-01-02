package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.Room;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Bookings", description = "Operaciones REST de reservas")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingDomain bookingDomain;

    public BookingController(BookingDomain bookingDomain) {
        this.bookingDomain = bookingDomain;
    }

    @Operation(summary = "Obtener reserva por id")
    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable String id) {
        return bookingDomain.getBooking(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las reservas")
    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(bookingDomain.getAllBookings());
    }

    @Operation(summary = "Obtener reservas por huesped")
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Booking>> findByGuest(@PathVariable String guestId) {
        return ResponseEntity.ok(bookingDomain.getBookingsByGuest(guestId));
    }

    @Operation(summary = "Obtener reservas por habitacion")
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Booking>> findByRoom(@PathVariable String roomId) {
        return ResponseEntity.ok(bookingDomain.getBookingsByRoom(roomId));
    }

    @Operation(summary = "Obtener habitacion de una reserva")
    @GetMapping("/{id}/room")
    public ResponseEntity<Room> getRoomFromBooking(@PathVariable String id) {
        return bookingDomain.getRoomFromBooking(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener huesped de una reserva")
    @GetMapping("/{id}/guest")
    public ResponseEntity<Guest> getGuestFromBooking(@PathVariable String id) {
        return bookingDomain.getGuestFromBooking(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear reserva")
    @PostMapping
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingDomain.createBooking(booking));
    }

    @Operation(summary = "Actualizar reserva")
    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable String id, @Valid @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingDomain.updateBooking(id, booking));
    }

    @Operation(summary = "Cancelar reserva")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookingDomain.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}
