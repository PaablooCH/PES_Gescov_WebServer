package com.gescov.webserver;

import com.gescov.webserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

public class WebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    MessageService messageService;


    @Override
    protected  void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = String.valueOf(message.getPayload());

        if ("1".equals(msg)) {
            session.sendMessage(new TextMessage("He recibido el mensaje"));
        } else {
            session.sendMessage(new TextMessage("Esto es default"));
        }
    }
}
