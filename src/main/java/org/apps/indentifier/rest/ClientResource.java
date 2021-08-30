package org.apps.indentifier.rest;

import org.apps.indentifier.dto.ClientDto;
import org.apps.indentifier.service.ClientService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client")
public class ClientResource {

    @Inject
    ClientService clientService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200",
            description = "Fetches client data.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ClientDto.class)))
    @APIResponse(responseCode = "500", description = "Unable to fetch client.")
    @Operation(summary = "Fetches client data as per passed parameters",
            description = "Fetches client data as per passed parameters")
    public Response getClient(@QueryParam("idNumber") String idNumber,
                              @QueryParam("firstName") String name,
                              @QueryParam("phoneNumber") String mobileNumber) {

        return clientService.findClient(idNumber, name, mobileNumber).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200",
            description = "Creates client.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ClientDto.class)))
    @APIResponse(responseCode = "500", description = "Unable to create client.")
    @Operation(summary = "Creates client with provided data.",
            description = "Creates client with provided data.")
    public Response createClient(@Valid ClientDto client) {
        return clientService.createClient(client).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path(value = "/{id}")
    @APIResponse(responseCode = "200",
            description = "Updates client.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = ClientDto.class)))
    @APIResponse(responseCode = "500", description = "Unable to update client.")
    @Operation(summary = "Updates client with provided data.",
            description = "Updates client with provided data.")
    public Response updateClient(@PathParam("id")Long clientId, @Valid ClientDto client) {
        return clientService.updateClient(clientId, client).build();
    }
}
