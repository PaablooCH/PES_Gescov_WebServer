package com.gescov.webserver.service;

import com.gescov.webserver.dao.chat.ChatDao;
import com.gescov.webserver.exception.AlreadyExistsException;
import com.gescov.webserver.exception.ChatAlreadyExistsException;
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
        if(findSameChat(chat.getPartA(), chat.getPartB())) throw new ChatAlreadyExistsException(Chat.class, chat.getPartA(), chat.getPartB());
        boolean t1 = checkUsers(chat.getPartA());
        boolean t2 = checkUsers(chat.getPartB());
        if(t1){
            if(t2) throw new OnlyStudentTeacherChatException();
        }
        else {
            if (!t2) throw new OnlyStudentTeacherChatException();
        }
        Chat c = chatDao.insert(chat);
        String u1 = getUserName(chat.getPartA());
        String pic1 = getUserPicture(chat.getPartA());
        String u2 = getUserName(chat.getPartB());
        String pic2 = getUserPicture(chat.getPartB());
        chatPreviewService.createChatPreview(new ChatPreview(c.getId(),u1,pic1,u2,pic2));
        return c;
    }

    public Optional<Chat> getChatById(String id){ return chatDao.findById(id); }

    private boolean checkUsers(String part) {
        Optional<User> uA = userService.getUserById(part);
        if (uA.isEmpty()) throw new NotFoundException(User.class, part);
        return !uA.get().isStudent();
    }

    private String getUserName(String part) {
        Optional<User> uA = userService.getUserById(part);
        if (uA.isEmpty()) throw new NotFoundException(User.class, part);
        return uA.get().getName();
    }

    private String getUserPicture(String part) {
        Optional<User> uA = userService.getUserById(part);
        if (uA.isEmpty()) throw new NotFoundException(User.class, part);
        return uA.get().getPic();
    }

    private boolean findSameChat(String partA, String partB){
        Optional<Chat> option1 = chatDao.findByPartAAndPartB(partA, partB);
        if(!option1.isEmpty()) return true;
        Optional<Chat> option2 = chatDao.findByPartAAndPartB(partB, partA);
        if(!option2.isEmpty()) return true;
        return false;
    }
}
