package org.sample.whiteboardapp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
//@ x S erver  Endpoin  t(value = "/whiteb   oarde   ndpoint", encoders = {Figu reEncoder.class}, decode rs = {Fig   x  ureDecoder.class})
public class xxxxMyWhiteboarxxxxxd {

//    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
//
//    @OnMessage
//    public void broadcastFigure(Figure figure, Session session) throws IOException, EncodeException {
//        System.out.println("broadcastFigure: " + figure);
//        for (Session peer : peers) {
//            if (!peer.equals(session)) {
//                peer.getBasicRemote().sendObject(figure);
//            }
//        }
//    }
//
//    @OnMessage
//    public void broadcastSnapshot(ByteBuffer data, Session session) throws IOException {
//        System.out.println("broadcastBinary: " + data);
//        for (Session peer : peers) {
//            if (!peer.equals(session)) {
//                peer.getBasicRemote().sendBinary(data);
//            }
//        }
//    }
//
//    @OnOpen
//    public void onOpen(Session peer) {
//        peers.add(peer);
//    }
//
//    @OnClose
//    public void onClose(Session peer) {
//        peers.remove(peer);
//    }
//
//    @OnError
//    public void onError(Throwable t) {
//        System.out.println("MyWhiteboard endpoint error" + t);
//    }
}
