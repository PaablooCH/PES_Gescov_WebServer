package com.gescov.webserver.service;

import com.gescov.webserver.dao.chatPreview.ChatPreviewDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Chat;
import com.gescov.webserver.model.ChatPreview;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Chat> c = chatService.getChatById(cp.getChatID());
        if(c.isEmpty()) throw new NotFoundException(Chat.class, cp.getChatID());
        return chatPreviewDao.insert(cp);
    }

    public List<ChatPreview> getChatsFromUserID(String userID){
        Optional<User> u = userService.getUserById(userID);
        List<ChatPreview> ch = chatPreviewDao.findAllByUserNameA(u.get().getName());
        ch.addAll(chatPreviewDao.findAllByUserNameB(u.get().getName()));
        return ch;
    }

    public ChatPreview updateLastMessage(String chatID, String text, String hour){
        Optional<ChatPreview> ch = chatPreviewDao.findByChatID(chatID);
        ch.get().setLastText(text);
        ch.get().setLastTextHour(hour);
        return chatPreviewDao.save(ch.get());
    }
}











