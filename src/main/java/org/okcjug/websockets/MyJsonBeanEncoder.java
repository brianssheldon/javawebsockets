package org.okcjug.websockets;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MyJsonBeanEncoder implements Encoder.Text<MyJsonBean> {

    @Override
    public String encode(MyJsonBean bean) throws EncodeException {
        return bean.getJson().toString();
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
