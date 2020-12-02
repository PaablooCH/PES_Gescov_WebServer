package com.gescov.webserver.service;

import com.gescov.webserver.dao.chat.ChatDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.OnlyStudentTeacherChatException;
import com.gescov.webserver.model.Chat;
import com.gescov.webserver.model.ChatPreview;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    ChatDao chatDao;

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    ChatPreviewService chatPreviewService;

    public Chat createChat(Chat chat) {
        Boolean t1 = checkUsers(chat.getPartA());
        Boolean t2 = checkUsers(chat.getPartB());
        if(t1){
            if(t2) throw new OnlyStudentTeacherChatException();
        }
        else {
            if (!t2) throw new OnlyStudentTeacherChatException();
        }
        Chat c = chatDao.insert(chat);
        String u1 = getUserName(chat.getPartA());
        String u2 = getUserName(chat.getPartB());
        chatPreviewService.createChatPreview(new ChatPreview(c.getId(),u1,u2));
        return c;
    }

    public Optional<Chat> getChatById(String id){ return chatDao.findById(id); }

    private boolean checkUsers(String part) {
        Optional<User> uA = userService.getUserById(part);
        if (uA.isEmpty()) throw new NotFoundException(User.class, part);
        if(uA.get().getProfile().equals("Teacher")) return true;
        return false;
    }

    private String getUserName(String part) {
        Optional<User> uA = userService.getUserById(part);
        if (uA.isEmpty()) throw new NotFoundException(User.class, part);
        return uA.get().getName();
    }

}
