package com.gescov.webserver.service;

import com.gescov.webserver.dao.message.MessageDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Chat;
import com.gescov.webserver.model.Message;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageDao messageDao;

    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    @Autowired
    ChatPreviewService chatPreviewService;

    public Message createMessage(Message m){
        Optional<User> u = userService.getUserById(m.getCreatorID());
        if(u.isEmpty()) throw new NotFoundException(User.class, m.getCreatorID());
        Optional<Chat> c = chatService.getChatById(m.getChatID());
        if(c.isEmpty()) throw new NotFoundException(Chat.class, m.getChatID());
        chatPreviewService.updateLastMessage(m.getChatID(),m.getText(),m.getHour(), m.getDate());
        return messageDao.insert(m);
    }

    public Optional<Message> getMessageByID(String messageID) { return messageDao.findById(messageID); }

    public List<Message> getMessagesByChatID(String chatID){ return messageDao.findAllByChatID(chatID); }
}
