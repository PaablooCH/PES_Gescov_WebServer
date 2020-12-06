package com.gescov.webserver;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

public class WebSocketHandler extends AbstractWebSocketHandler {


    @Override
    protected  void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = String.valueOf(message.getPayload());

        switch (msg){
            case("1"):
                System.out.println("Recibido 1");
                session.sendMessage(new TextMessage("Recibido"));
                break;
            default:
                System.out.println("Connected client");
        }
    }
}
