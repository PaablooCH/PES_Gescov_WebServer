package com.gescov.webserver.service;

import com.gescov.webserver.dao.chat.ChatDao;
import com.gescov.webserver.exception.NotFoundException;
import com.gescov.webserver.exception.OnlyStudentTeacherChatException;
import com.gescov.webserver.model.Chat;
import com.gescov.webserver.model.Contagion;
import com.gescov.webserver.model.Message;
import com.gescov.webserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Chat createChat(Chat chat) {
        Boolean t1 = checkUsers(chat.getPartA());
        Boolean t2 = checkUsers(chat.getPartB());
        if(t1){
            if(t2) throw new OnlyStudentTeacherChatException();
        }
        else{
            if(!t2) throw new OnlyStudentTeacherChatException();
        }
        return chatDao.insert(chat);
    }

    public Optional<Chat> getChatById(String id){ return chatDao.findById(id); }

   /* public List<Chat> getChatsByUserId(String userID){
        List<Chat> chats = new ArrayList<>();
        chats = chatDao.findByPartA(userID);
        chats.addAll(chatDao.findByPartB(userID));
        List<User> us = getUsers(chats, userID);
        List<Message> m = get
        List<Pair<String, String>> aux = new ArrayList<>();
        for(int i = 0; i < chats.size(); i++) aux.add(Pair.of(us.get(i).getName(), con.get(i).getStartContagion()));
        return chats;
    };*/

    private List<User> getUsers(List<Chat> chat, String userID) {
        List<String> partIDs = new ArrayList<>();
        for (Chat ch : chat) {
            if(ch.getPartA().equals(userID)) partIDs.add(ch.getPartB());
            else partIDs.add(ch.getPartA());
        }
        return userService.findAllByIDIn(partIDs);
    }

    private boolean checkUsers(String part) {
        Optional<User> uA = userService.getUserById(part);
        if (uA.isEmpty()) throw new NotFoundException(User.class, part);
        if(uA.get().getProfile().equals("Teacher")) return true;
        return false;
    }

}
