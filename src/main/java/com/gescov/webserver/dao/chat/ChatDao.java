package com.gescov.webserver.dao.chat;

import com.gescov.webserver.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatDao extends MongoRepository<Chat, String> {

    List<Chat> findAllByPartA(String userID);

    List<Chat> findAllByPartB(String userID);

    Optional<Chat> findByPartAAndPartB(String partA, String partB);

}
