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

        if(msg instanceof String) session.sendMessage(new TextMessage(msgJson));
        else System.out.println("Connected client");
*/
        switch (msg){
            case("1"):
                System.out.println("Recibido 1");
                session.sendMessage(new TextMessage("Lo he recibido"));
                break;
            default:
                System.out.println("Connected client");
        }

    }
}
