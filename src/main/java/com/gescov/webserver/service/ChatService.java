package com.gescov.webserver.service;

import com.gescov.webserver.dao.chat.ChatDao;
import com.gescov.webserver.exception.ChatAlreadyExistsException;
import com.gescov.webserver.exception.ChatWithSamePersonNotAllowedException;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.ChatBetweenStudentsNotPermitedException;
import com.gescov.webserver.model.Chat;
import com.gescov.webserver.model.ChatPreview;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        if(chat.getPartA().equals(chat.getPartB())) throw new ChatWithSamePersonNotAllowedException();
        if(findSameChat(chat.getPartA(), chat.getPartB())) throw new ChatAlreadyExistsException(Chat.class, chat.getPartA(), chat.getPartB());
        boolean t1 = checkUsers(chat.getPartA());
        boolean t2 = checkUsers(chat.getPartB());
        if(!t1 && !t2) throw new ChatBetweenStudentsNotPermitedException();
        Chat c = chatDao.insert(chat);
        String u1 = getUserName(chat.getPartA());
        String pic1 = getUserPicture(chat.getPartA());
        String u2 = getUserName(chat.getPartB());
        String pic2 = getUserPicture(chat.getPartB());
        chatPreviewService.createChatPreview(new ChatPreview(c.getId(),u1,pic1,u2,pic2, LocalDateTime.now()));
        return c;
    }

    public Chat getChatById(String id) {
        Optional<Chat> ch = chatDao.findById(id);
        if(ch.isEmpty()) throw new NotFoundException(Chat.class, id);
        return ch.get();
    }

    public boolean isParticipant(String chatID, String creator){
        Chat ch = getChatById(chatID);
        if (ch.getPartA().equals(creator)) return true;
        return ch.getPartB().equals(creator);
    }

    private boolean checkUsers(String part) {
        return !userService.getUserById(part).isStudent();
    }

    private String getUserName(String part) {
        return userService.getUserById(part).getName();
    }

    private String getUserPicture(String part) {
        return userService.getUserById(part).getPic();
    }

    private boolean findSameChat(String partA, String partB){
        Optional<Chat> option1 = chatDao.findByPartAAndPartB(partA, partB);
        if (option1.isPresent()) return true;
        Optional<Chat> option2 = chatDao.findByPartAAndPartB(partB, partA);
        return option2.isPresent();
    }

    public List<String> getChatsIDsOfUser(String userID){
        User u = userService.getUserById(userID);
        List<Chat> ch = chatDao.findAllByPartA(u.getId());
        ch.addAll(chatDao.findAllByPartB(u.getId()));
        if(ch.isEmpty()) throw new NotFoundException(Chat.class, userID);
        List<String> ids = new ArrayList<>();
        for(Chat c : ch){
            ids.add(c.getId());
        }
        return ids;
    }
}
