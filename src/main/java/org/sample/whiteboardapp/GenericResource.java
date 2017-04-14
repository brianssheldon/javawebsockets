package org.sample.whiteboardapp;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 */
@Path("/xx")
public class GenericResource {

//    @Context
//    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
//    public GenericResource() {
//    }

    /**
     * Retrieves representation of an instance of org.sample.whiteboardapp.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/tilecannon")
    public String getXml() {
        return "aaaaaaaaassssssssssssdddddddddddfffffffffffff";
    }

//    /**
//     * PUT method for updating or creating an instance of GenericResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
}
