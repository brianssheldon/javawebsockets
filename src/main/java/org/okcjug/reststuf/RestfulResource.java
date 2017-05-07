package org.okcjug.reststuf;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.okcjug.websockets.MyJsonBean;
import org.okcjug.websockets.MyJsonBeanDecoder;
import org.okcjug.websockets.NewMarker;

@Path("/greeting")
public class RestfulResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String theGetMethod() {
        return "howdy from RestfulResource theGetMethod";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void thePostMethod(String content) throws IOException, EncodeException, DecodeException {
        System.out.println("RestfulResource thePostMethod " + content);
        
        MyJsonBeanDecoder decoder = new MyJsonBeanDecoder();
        Set<Session> peers = NewMarker.peers;
        
        List<MyJsonBean> markers = NewMarker.markers;
        markers.add(decoder.decode(content));
        
        System.out.println(decoder.willDecode(content));
        
        for (Session peer : peers) {
                peer.getBasicRemote().sendObject(decoder.decode(content));
        }
    }
}