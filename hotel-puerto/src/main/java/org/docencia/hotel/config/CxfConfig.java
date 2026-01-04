package org.docencia.hotel.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.docencia.hotel.web.soap.BookingSoapService;
import org.docencia.hotel.web.soap.GuestSoapService;
import org.docencia.hotel.web.soap.HotelSoapService;
import org.docencia.hotel.web.soap.RoomSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private GuestSoapService guestSoapService;

    @Autowired
    private HotelSoapService hotelSoapService;

    @Autowired
    private RoomSoapService roomSoapService;

    @Autowired
    private BookingSoapService bookingSoapService;

    @Bean
    public Endpoint guestEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, guestSoapService);
        endpoint.publish("/guest");
        return endpoint;
    }

    @Bean
    public Endpoint hotelEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, hotelSoapService);
        endpoint.publish("/hotel");
        return endpoint;
    }

    @Bean
    public Endpoint roomEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, roomSoapService);
        endpoint.publish("/room");
        return endpoint;
    }

    @Bean
    public Endpoint bookingEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, bookingSoapService);
        endpoint.publish("/booking");
        return endpoint;
    }
}
