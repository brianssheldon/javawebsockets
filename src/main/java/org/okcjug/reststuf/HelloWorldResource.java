package org.okcjug.reststuf;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class HelloWorldResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getGreeting() {
        return "howdy from helloworldresource getgreeting";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setNamex(String content) {
        System.out.println("setNamex " + content);
    }
}