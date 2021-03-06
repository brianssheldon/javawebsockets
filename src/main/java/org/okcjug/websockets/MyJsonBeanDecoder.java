package org.okcjug.websockets;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MyJsonBeanDecoder implements Decoder.Text<MyJsonBean> {

    @Override
    public MyJsonBean decode(String string) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(string)).readObject();
        return new MyJsonBean(jsonObject);
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean willDecode(String string) {
        try {
            System.out.println("willDecode" + string);
            Json.createReader(new StringReader(string)).readObject();
            return true;
        } catch (JsonException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}