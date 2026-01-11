## 1. Estructura del proyecto y capas

El proyecto sigue una arquitectura en capas inspirada en el modelo hexagonal, separando claramente **web**, **dominio**, **servicio** y **persistencia** para favorecer el desacoplamiento y las pruebas unitarias.

- **Capa web (`org.docencia.hotel.web`)**  
  - `rest`: controladores REST que exponen los endpoints HTTP y solo conocen la capa de dominio.  
  - `soap`: servicios SOAP (interfaces y sus implementaciones) que igualmente delegan toda la lógica en el dominio.

- **Capa de dominio (`org.docencia.hotel.domain`)**  
  - `api`: interfaces de dominio (`HotelDomain`, `RoomDomain`, `BookingDomain`, `GuestDomain`) que definen los casos de uso.  
  - `impl`: implementaciones como `BookingDomainImpl` y `GuestDomainImpl`, que orquestan llamadas a servicios y combinan datos (por ejemplo, guest en H2 y preferencias en MongoDB).  
  - `model`: modelos de dominio (`Hotel`, `Room`, `Booking`, `Guest`, `GuestPreferences`) usados de forma homogénea por REST, SOAP y servicios.

- **Capa de servicio (`org.docencia.hotel.service`)**  
  - `api`: interfaces de servicio (`HotelService`, `RoomService`, `BookingService`, `GuestService`, etc.).  
  - `impl`: implementaciones que usan repositorios de persistencia y mappers MapStruct para transformar entre entidades/documentos y modelos de dominio.

- **Capa de persistencia (`org.docencia.hotel.persistence`)**  
  - `jpa.entity`: entidades JPA (`HotelEntity`, `RoomEntity`, `BookingEntity`, `GuestEntity`) mapeadas a H2 para datos relacionales.
  - `jpa.repository`: repositorios JPA (por ejemplo, `HotelRepository`, `RoomRepository`, `BookingRepository`, `GuestJpaRepository`) basados en Spring Data.
  - `nosql.document`: documento MongoDB `GuestPreferencesDocument` para las preferencias de huésped.
  - `nosql.repository`: repositorio de MongoDB (`GuestPreferencesRepository`) para acceder a dichas preferencias.

- **Mappers (`org.docencia.hotel.mapper`)**  
  - `jpa`: mappers MapStruct (`GuestMapper`, `HotelMapper`, `RoomMapper`, `BookingMapper`) que transforman entre modelos de dominio y entidades JPA.
  - `nosql`: mapper para `GuestPreferencesDocument` y el modelo de dominio `GuestPreferences`.

La interacción típica es:  
REST/SOAP → Dominio (caso de uso) → Servicios → Repositorios (JPA/Mongo) → BBDD.  
El caso polyglot de **Guest** se resuelve combinando datos de H2 (huésped y reservas) con preferencias en MongoDB usando `GuestDomainImpl` y `GuestService`/`GuestPreferencesService`.

***

## 2. Servicio REST y endpoints Swagger

La capa REST expone los controladores bajo el paquete `org.docencia.hotel.web.rest`, con rutas base tipo `/api/...` y está documentada automáticamente con **springdoc-openapi**, lo que genera Swagger UI en `/swagger-ui/index.html` y el JSON de OpenAPI en `/v3/api-docs`.

- **Guest REST**  
  - `GET /api/guests`: obtiene la lista de huéspedes desde el dominio (`GuestDomain.getAllGuests`).  
  - `GET /api/guests/{id}`: devuelve un huésped y, si existen, sus preferencias cargadas desde MongoDB, siguiendo el patrón polyglot (guest en H2, preferencias en Mongo).
  - `GET /api/guests/{id}/preferences`: devuelve solo las preferencias de un huésped; si no existen, responde 404.  
  - `POST /api/guests`: crea un nuevo huésped en H2 a través de `GuestDomain.createGuest`.  
  - `PUT /api/guests/{id}`: actualiza los datos del huésped.  
  - `POST /api/guests/{guestId}/preferences`: crea o guarda preferencias en MongoDB asociadas al huésped indicado.  
  - `PUT /api/guests/{guestId}/preferences`: actualiza las preferencias de un huésped en MongoDB.  

- **Hotel REST**  
  - Endpoints típicos CRUD como `GET /api/hotels`, `GET /api/hotels/{id}`, `POST /api/hotels`, `PUT /api/hotels/{id}`, `DELETE /api/hotels/{id}`, todos delegando en `HotelDomain` y `HotelService` y usando `HotelMapper` para la transformación.

- **Room REST**  
  - Endpoints similares para habitaciones: listado, detalle, alta, modificación y baja, basados en `RoomDomain` y `RoomService`, mapeando `Room`↔`RoomEntity` con `RoomMapper`.

- **Booking REST**  
  - `GET /api/bookings` y `GET /api/bookings/{id}`: obtienen reservas desde la capa de dominio (`BookingDomain.getAllBookings` / `getBooking`).  
  - `GET /api/bookings/guest/{guestId}`: reservas de un huésped concreto.  
  - `GET /api/bookings/room/{roomId}`: reservas asociadas a una habitación.  
  - `POST /api/bookings`: crea una reserva usando `BookingDomain.createBooking`.  
  - `PUT /api/bookings/{id}`: actualiza una reserva (fechas, estado, etc.).  
  - `DELETE /api/bookings/{id}`: cancela/elimina una reserva a través de la capa de dominio.

Los endpoints están anotados con las típicas anotaciones de Spring MVC (`@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`) y documentados con anotaciones de springdoc como `@Tag`, `@Operation` y `@ApiResponses`, visibles en Swagger UI.

***

## 3. Servicios SOAP

Además de REST, el proyecto expone servicios SOAP bajo el paquete `org.docencia.hotel.web.soap`, configurados con Apache CXF y las anotaciones JAX-WS.

- Cada entidad principal (por ejemplo, Guest, Hotel, Room, Booking) tiene:  
  - Una **interfaz SOAP** annotada con `@WebService(name = ..., targetNamespace = "http://hotel.docencia.org/ws")`.  
  - Una **implementación** annotada con `@WebService(endpointInterface = ..., serviceName = "...SoapService", portName = "...SoapPort")` que delega en el dominio correspondiente.

- El contexto CXF se publica en una ruta común (por ejemplo, `/services`), de manera que:  
  - El endpoint SOAP de Guest se expone en `http://localhost:8080/services/guest`.  
  - El WSDL correspondiente está disponible en `http://localhost:8080/services/guest?wsdl`.  
  - De forma análoga se exponen servicios para Hotel, Room y Booking según la convención descrita en la documentación del proyecto.

Las operaciones SOAP suelen reflejar la misma lógica que los endpoints REST: obtener entidad por id, listar, crear, actualizar y eliminar, pero usando contratos WSDL y tipos generados/serializados según JAX-WS, manteniendo el patrón de capas: SOAP → Dominio → Servicios → Persistencia.

***

## 4. Tests y cobertura

El proyecto está preparado para pruebas unitarias con **JUnit 5**, soporte para mocks y generación de cobertura con **JaCoCo**, integrados en el ciclo Maven (`mvn clean test`).

- **Modelo de dominio**  
  - Clases como `Guest`, `Hotel`, `Room`, `Booking` y `GuestPreferences` tienen tests que cubren constructores, getters/setters, `equals` y `hashCode`, asegurando la correcta manipulación de datos básicos.

- **Persistencia JPA**  
  - Entidades `GuestEntity`, `HotelEntity`, `RoomEntity` y `BookingEntity` están testeadas de forma similar, comprobando mapeos de campos y comportamiento de igualdad/hash para su uso en colecciones y JPA.

- **Persistencia MongoDB**  
  - El documento `GuestPreferencesDocument` dispone de pruebas para constructores, getters/setters y `equals`/`hashCode`, verificando la parte NoSQL de la persistencia polyglot.

- **Capa de dominio**  
  - Implementaciones como `BookingDomainImpl` y `GuestDomainImpl` se prueban usando dobles simples (fakes) o servicios simulados en memoria, validando la lógica de orquestación: creación y actualización de reservas, obtención de reservas por huésped o habitación, y el caso específico de obtención/guardado de preferencias de huésped contra MongoDB.

- **Ejecutar y revisar cobertura**  
  - Los tests se ejecutan con `mvn clean test` y el informe de JaCoCo se genera en `target/site/jacoco/index.html`, permitiendo revisar gráficamente la cobertura por paquete, clase y método.

En conjunto, la combinación de pruebas sobre modelos, entidades/documentos y lógica de dominio garantiza que REST y SOAP se apoyan en un núcleo bien verificado, y que el escenario polyglot H2 + MongoDB para `Guest` y sus preferencias funciona según lo esperado.