package org.okcjug.reststuf;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

@Path("/greeting")
public class HelloWorldResource {

    @GET
    @Produces("text/html")
    public String getGreeting() {
        return "howdy from helloworldresource getgreeting";
    }

    @PUT
    @Consumes("text/plain")
    public void setName(String content) {
        System.out.println("setName " + content);
    }
}
