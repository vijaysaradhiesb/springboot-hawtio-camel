
package com.customs.declaration.rest;

import org.apache.camel.Body;
import org.apache.camel.Header;
import uk.customs.declaration.beans.Declaration;
import uk.customs.declaration.beans.DeclarationResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * Author : Vijay Saradhi
 */

@Path("/")
public interface DeclarationService {

    @GET
    @Path("/getDeclarationDetails")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Declaration getDeclarationDetails(@QueryParam("identifier") @Header("identifier") String identifier);

    @POST
    @Path("/persistDeclaration")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    DeclarationResponse persistDeclaration(@Body Declaration object);

    @POST
    @Path("/publishActiveMQ")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    DeclarationResponse publishActiveMQ(@Body Declaration object);

    @POST
    @Path("/publishRabbitMQ")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    DeclarationResponse publishRabbitMQ(@Body Declaration object);


}



