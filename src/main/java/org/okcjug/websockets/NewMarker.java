package org.okcjug.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// http://localhost:8080/javawebsockets/

@ServerEndpoint(value = "/newmarkerendpoint", encoders = {MyJsonBeanEncoder.class}, decoders = {MyJsonBeanDecoder.class})
public class NewMarker {

    public static List<MyJsonBean> markers = new ArrayList();

    public static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(MyJsonBean thebean, Session session) throws IOException, EncodeException {

        System.out.println("-------------------------broadcastFigure");
        JsonObject jo = thebean.getJson();

        if (jo.getInt("id") == -1) {

            for (int i = 0; i < markers.size(); i++) {

                if (markers.get(i).getJson().getJsonNumber("lng").equals(jo.getJsonNumber("lng"))
                        && markers.get(i).getJson().getJsonNumber("lat").equals(jo.getJsonNumber("lat"))) {

                    for (Session peer : peers) {
                        if (!peer.equals(session)) {
                            System.out.println("sending delete " + jo);
                            thebean.setJson(jo);
                            peer.getBasicRemote().sendObject(thebean);
                        }
                    }
                    markers.remove(i);
                }
            }
        } else {
            markers.add(thebean);
            System.out.println("emitting markers: " + markers);
            for (Session peer : peers) {
                if (!peer.equals(session)) {
                    peer.getBasicRemote().sendObject(thebean);
                }
            }
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
        try {
            System.out.println("sending all markers onOpen " + markers.size());
            peer.getBasicRemote().sendObject(markers);
        } catch (IOException | EncodeException ex) {
            Logger.getLogger(NewMarker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnClose
    public void onClose(Session peer) {
        System.err.println("onClose " + peer);
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("NewMarker endpoint error " + t);
    }
}
