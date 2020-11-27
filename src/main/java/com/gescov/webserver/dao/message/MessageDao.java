package com.gescov.webserver.dao.message;

import com.gescov.webserver.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends MongoRepository<Message, String>, MessageDaoCustom<Message, String> {

}
