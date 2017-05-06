package org.sample.whiteboardapp;

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

@ServerEndpoint(value = "/newmarkerendpoint", encoders = {FigureEncoder.class}, decoders = {FigureDecoder.class})
public class NewMarker {
    
    private static List<Figure> markers = new ArrayList();

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(Figure figure, Session session) throws IOException, EncodeException {
        
        JsonObject jo = figure.getJson();
        
        System.out.println("xxxx broadcastFigure: " + figure.getJson().getInt("id"));
        
        if(figure.getJson().getInt("id") == -1){
            
            for(int i =0; i < markers.size(); i++){
                
                if(markers.get(i).getJson().getJsonNumber("lng").equals(figure.getJson().getJsonNumber("lng")) 
                && markers.get(i).getJson().getJsonNumber("lat").equals(figure.getJson().getJsonNumber("lat"))){
                    
//                    jo.put("deleteme", JsonValue.TRUE);
                    
                    for (Session peer : peers) {
                        if (!peer.equals(session)) {
                            System.out.println("sending delete " + jo);
                            figure.setJson(jo);
                            peer.getBasicRemote().sendObject(figure);
                        }
                    }
                    markers.remove(i);
                }
            }
        }else{        
            markers.add(figure);
            System.out.println("xx markers: " + markers);
            for (Session peer : peers) {
                if (!peer.equals(session)) {
                    peer.getBasicRemote().sendObject(figure);
                }
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
