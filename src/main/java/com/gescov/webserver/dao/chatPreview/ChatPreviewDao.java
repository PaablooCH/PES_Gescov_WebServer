package com.gescov.webserver.dao.chatPreview;

import com.gescov.webserver.model.ChatPreview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatPreviewDao extends MongoRepository<ChatPreview, String>, ChatPreviewDaoCustom<ChatPreview, String> {

    ChatPreview findByChatID(String chatID);

    List<ChatPreview> findAllByUserNameA(String username);

    List<ChatPreview> findAllByUserNameB(String username);

}
