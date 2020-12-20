package com.gescov.webserver.service;

import com.gescov.webserver.dao.message.MessageDao;
import com.gescov.webserver.exception.NotAParticipantException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.model.Chat;
import com.gescov.webserver.model.ChatPreview;
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
        userService.existsUser(m.getCreatorID());
        Optional<Chat> c = chatService.getChatById(m.getChatID());
        if(c.isEmpty()) throw new NotFoundException(Chat.class, m.getChatID());
        if(!chatService.isParticipant(m.getChatID(),m.getCreatorID())) throw new NotAParticipantException(User.class, m.getCreatorID(), m.getChatID());
        chatPreviewService.updateLastMessage(m.getChatID(),m);
        return messageDao.insert(m);
    }

    public Message getLastMessageOfChat(String chatID){
        ChatPreview cp = chatPreviewService.getChatPreviewByChatID(chatID);
        return cp.getLastMessage();
    }


    public Optional<Message> getMessageByID(String messageID) { return messageDao.findById(messageID); }

    public List<Message> getMessagesByChatID(String chatID){ return messageDao.findAllByChatID(chatID); }
}
