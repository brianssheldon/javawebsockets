package org.sample.whiteboardapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/newmarkerendpoint", encoders = {FigureEncoder.class}, decoders = {FigureDecoder.class})
public class NewMarker {
    
    private static List markers = new ArrayList();

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(Figure figure, Session session) throws IOException, EncodeException {
        
//        SqliteReader sr = new SqliteReader();
//        sr.getTile(10, 234, 403);
        
        System.out.println("xxxx broadcastFigure: " + figure.getJson().getInt("id"));
        
        if(figure.getJson().getInt("id") == -1){
//            xxxx broadcastFigure: {"id":-1,"lng":-97.63289036390321,"lat":35.580609327321426}
            System.out.println("delete received" + figure.getJson().getJsonNumber("lng") + "  " + figure.getJson().getJsonNumber("lat"));
        }
        markers.add(figure);
        System.out.println("xx markers: " + markers);
        
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(figure);
            }
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
        try {
            System.out.println("sending markers onOpen " + markers.size());
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
        System.out.println("NewMarker endpoint error" + t);
    }
}
