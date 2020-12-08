package com.gescov.webserver;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gescov.webserver.model.ChatPreview;
import com.gescov.webserver.model.Message;
import com.gescov.webserver.service.ChatService;
import com.gescov.webserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Optional;

public class WebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    MessageService messageService;


    @Override
    protected  void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = String.valueOf(message.getPayload());

        /*
        Message m = messageService.getLastMessageOfChat(msg);
        String msgJson = new ObjectMapper().writeValueAsString(m);
        */

        switch (msg){
            default:
                session.sendMessage(new TextMessage("Prueba numero 2"));
        }
    }
}
