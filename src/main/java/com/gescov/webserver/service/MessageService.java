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

    @Autowired
    NotificationService notificationService;

    public Message createMessage(Message m) {
        String msgCreator = m.getCreatorID();
        User sender = userService.getUserById(msgCreator);
        Chat chat = chatService.getChatById(m.getChatID());
        if (!chatService.isParticipant(m.getChatID(), msgCreator)) throw new NotAParticipantException(User.class, msgCreator, m.getChatID());
        if (msgCreator.equals(chat.getPartA())) {
            User receiver = userService.getUserById(chat.getPartB());
            for (String d : receiver.getDevices()) {
                notificationService.sendNotiToDevice(d, sender.getName(), m.getText());
            }
        }
        else {
            User receiver = userService.getUserById(chat.getPartA());
            for (String d : receiver.getDevices()) {
                notificationService.sendNotiToDevice(d, sender.getName(), m.getText());
            }
        }
        chatPreviewService.updateLastMessage(m.getChatID(), m);
        return messageDao.insert(m);
    }

    public Message getLastMessageOfChat(String chatID) {
        ChatPreview cp = chatPreviewService.getChatPreviewByChatID(chatID);
        return cp.getLastMessage();
    }


    public Message getMessageByID(String messageID) {
        Optional<Message> message = messageDao.findById(messageID);
        if (message.isEmpty()) throw new NotFoundException(Message.class, messageID);
        return message.get();
    }

    public List<Message> getMessagesByChatID(String chatID) { return messageDao.findAllByChatID(chatID); }

}
