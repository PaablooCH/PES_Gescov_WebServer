package com.gescov.webserver.api;

import com.gescov.webserver.model.Chat;
import com.gescov.webserver.model.ChatPreview;
import com.gescov.webserver.model.Message;
import com.gescov.webserver.service.ChatPreviewService;
import com.gescov.webserver.service.ChatService;
import com.gescov.webserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class ChatController {

    private final ChatService chatService;
    private final ChatPreviewService chatPreviewService;
    private final MessageService messageService;

    @Autowired
    public ChatController(ChatService chatService, ChatPreviewService chatPreviewService, MessageService messageService){
        this.chatService = chatService;
        this.chatPreviewService = chatPreviewService;
        this.messageService = messageService;
    }

    @PostMapping(path = "api/chats")
    public Chat addChat(@NotNull @RequestBody Chat chat){
        return chatService.createChat(chat);
    }

    @MessageMapping("chats/new")
    @SendTo ("/topic/public")
    public Message addMessage(@Payload Message m) {
        return messageService.createMessage(m);
    }

    @GetMapping(path = "api/chats/{id}/messages")
    public List<Message> getChatMessages(@PathVariable ("id") String chatID){
        return messageService.getMessagesByChatID(chatID);
    }

    @GetMapping(path = "api/chats/previews")
    public List<ChatPreview> getUserChats(@NotNull  @RequestParam ("userID") String userID){
        return chatPreviewService.getChatsFromUserID(userID);
    }

    @GetMapping(path = "api/chats/{id}")
    public Optional<Chat> getChat(@NotNull @PathVariable ("id") String id){
        return chatService.getChatById(id);
    }
}
