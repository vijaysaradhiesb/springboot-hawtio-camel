package com.spring.boot.hawtio;

import org.apache.camel.Body;
import org.apache.camel.Header;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface SimpleService {

    String hello(String greeting);
    @GET
    @Path("/getDetails/{identifier}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    String getDetails(@PathParam("identifier") @Header("identifier") String identifier);


    @POST
    @Path("/notifyEvent")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Object notifyClaim(@Body Object object);
}


