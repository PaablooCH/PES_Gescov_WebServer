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
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/chats")
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

    @PostMapping
    public Chat addChat(@NotNull @RequestBody Chat chat){
        return chatService.createChat(chat);
    }

    @MessageMapping("/new")
    public void addMessage(@Payload @RequestBody Message m) {
        messageService.createMessage(m);
    }

    @GetMapping(path = "/{id}/messages")
    public List<Message> getChatMessages(@PathVariable ("id") String chatID){
        return messageService.getMessagesByChatID(chatID);
    }

    @GetMapping(path = "/previews")
    public List<ChatPreview> getUserChats(@NotNull  @RequestParam ("userID") String userID){
        return chatPreviewService.getChatsFromUserID(userID);
    }

    @GetMapping(path = "/{id}")
    public Optional<Chat> getChat(@NotNull @PathVariable ("id") String id){
        return chatService.getChatById(id);
    }

}
