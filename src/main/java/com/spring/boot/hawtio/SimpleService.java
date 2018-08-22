package com.spring.boot.hawtio;

import org.apache.camel.Header;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface SimpleService {

    String hello(String greeting);
    @GET
    @Path("/getDetails/{identifier}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    String getDetails(@PathParam("identifier") @Header("identifier") String identifier);


}


