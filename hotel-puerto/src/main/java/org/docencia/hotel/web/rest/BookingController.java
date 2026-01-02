package org.docencia.hotel.web.rest;

import org.docencia.hotel.domain.api.BookingDomain;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
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
    name = "Bookings",
    description = "Operaciones REST de reservas"
)
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingDomain bookingDomain;

    public BookingController(BookingDomain bookingDomain) {
        this.bookingDomain = bookingDomain;
    }

    @Operation(
        summary = "Obtener reserva por id",
        description = "Devuelve una reserva existente a partir de su identificador"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Reserva encontrada",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Reserva no encontrada",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable String id) {
        return bookingDomain.getBooking(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Listar todas las reservas",
        description = "Devuelve la lista completa de reservas registradas"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de reservas",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(bookingDomain.getAllBookings());
    }

    @Operation(
        summary = "Obtener reservas por huesped",
        description = "Devuelve todas las reservas realizadas por un huesped especifico"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de reservas del huesped",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        )
    })
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Booking>> findByGuest(@PathVariable String guestId) {
        return ResponseEntity.ok(bookingDomain.getBookingsByGuest(guestId));
    }

    @Operation(
        summary = "Obtener reservas por habitacion",
        description = "Devuelve todas las reservas asociadas a una habitacion especifica"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Listado de reservas de la habitacion",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        )
    })
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Booking>> findByRoom(@PathVariable String roomId) {
        return ResponseEntity.ok(bookingDomain.getBookingsByRoom(roomId));
    }

    @Operation(
        summary = "Obtener habitacion de una reserva",
        description = "Devuelve la habitacion asociada a una reserva especifica"
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
            description = "Reserva o habitacion no encontrada",
            content = @Content
        )
    })
    @GetMapping("/{id}/room")
    public ResponseEntity<Room> getRoomFromBooking(@PathVariable String id) {
        return bookingDomain.getRoomFromBooking(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Obtener huesped de una reserva",
        description = "Devuelve el huesped que realizo una reserva especifica"
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
            description = "Reserva o huesped no encontrado",
            content = @Content
        )
    })
    @GetMapping("/{id}/guest")
    public ResponseEntity<Guest> getGuestFromBooking(@PathVariable String id) {
        return bookingDomain.getGuestFromBooking(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Crear una nueva reserva",
        description = "Crea una nueva reserva a partir de los datos enviados en el cuerpo de la peticion"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Reserva creada correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingDomain.createBooking(booking));
    }

    @Operation(
        summary = "Actualizar una reserva existente",
        description = "Actualiza los datos de una reserva identificada por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Reserva actualizada correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Booking.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada no validos",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable String id, @Valid @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingDomain.updateBooking(id, booking));
    }

    @Operation(
        summary = "Cancelar una reserva",
        description = "Elimina una reserva existente identificada por su id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Reserva cancelada correctamente",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookingDomain.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}
