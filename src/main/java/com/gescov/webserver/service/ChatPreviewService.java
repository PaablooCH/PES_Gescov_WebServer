package com.gescov.webserver.service;

import com.gescov.webserver.dao.chatPreview.ChatPreviewDao;
import com.gescov.webserver.model.ChatPreview;
import com.gescov.webserver.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ChatPreviewService {

    @Autowired
    ChatPreviewDao chatPreviewDao;

    @Autowired
    ChatService chatService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    public ChatPreview createChatPreview(ChatPreview cp){
        chatService.getChatById(cp.getChatID());
        return chatPreviewDao.insert(cp);
    }

    public List<ChatPreview> getChatsFromUserID(String userID){
        List<String> ids = chatService.getChatsIDsOfUser(userID);
        List<ChatPreview> ch = new ArrayList<>();
        for(String id : ids){
            ChatPreview c = chatPreviewDao.findByChatID(id);
            ch.add(c);
        }
        ch.sort(Comparator.comparing(ChatPreview::getLastUpdate).reversed());
        return ch;
    }

    public ChatPreview getChatPreviewByChatID(String chatID){
        return chatPreviewDao.findByChatID(chatID);
    }

    public ChatPreview updateLastMessage(String chatID, Message m){
        ChatPreview ch = chatPreviewDao.findByChatID(chatID);
        ch.setLastMessage(m);
        ch.setLastUpdate(LocalDateTime.now());
        return chatPreviewDao.save(ch);
    }
}











