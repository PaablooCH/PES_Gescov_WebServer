package com.gescov.webserver.dao.message;

import com.gescov.webserver.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends MongoRepository<Message, String>, MessageDaoCustom<Message, String> {

    List<Message> findAllByChatID(String chatID);
}
