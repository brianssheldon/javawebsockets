package org.okcjug.reststuf;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class RestfulResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String theGetMethod() {
        return "howdy from RestfulResource theGetMethod";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void thePostMethod(String content) {
        System.out.println("RestfulResource thePostMethod " + content);
    }
}