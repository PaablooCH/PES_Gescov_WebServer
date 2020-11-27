package com.gescov.webserver.dao.chat;

import org.springframework.stereotype.Repository;

@Repository("chatMongo")
public class ChatDaoImpl<T, ID> implements ChatDaoCustom<T, ID> {
}
