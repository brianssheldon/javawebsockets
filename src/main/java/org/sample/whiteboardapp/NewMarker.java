package org.sample.whiteboardapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/newmarkerendpoint", encoders = {FigureEncoder.class}, decoders = {FigureDecoder.class})
public class NewMarker {
    
    List markers = new ArrayList();

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(Figure figure, Session session) throws IOException, EncodeException {
        
        System.out.println("xxxx broadcastFigure: " + figure);
        markers.add(figure);
        System.out.println("xx markers: " + markers);
        
        for (Session peer : peers) {
            System.out.println("in loop " + peer);
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(figure);
            }
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("NewMarker endpoint error" + t);
    }
}
