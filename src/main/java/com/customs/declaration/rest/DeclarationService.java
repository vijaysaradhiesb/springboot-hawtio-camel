package com.customs.declaration.rest;

import org.apache.camel.Body;
import org.apache.camel.Header;
import uk.customs.declaration.beans.Declaration;
import uk.customs.declaration.beans.DeclarationResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface DeclarationService {

    @GET
    @Path("/getDetails")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    String getDetails(@QueryParam("identifier") @Header("identifier") String identifier);


    @POST
    @Path("/declaration")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    DeclarationResponse declaration(@Body Declaration object);
}


