package org.docencia.hotel.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.docencia.hotel.web.soap.GuestSoapService;
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

    @Bean
    public Endpoint guestEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, guestSoapService);
        endpoint.publish("/guest");
        return endpoint;
    }
}
