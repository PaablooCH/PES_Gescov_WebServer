package com.gescov.webserver.dao.chat;

import com.gescov.webserver.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatDao extends MongoRepository<Chat, String>, ChatDaoCustom<Chat, String> {

    List<Chat> findByPartA(String userID);

    List<Chat> findByPartB(String userID);
}
