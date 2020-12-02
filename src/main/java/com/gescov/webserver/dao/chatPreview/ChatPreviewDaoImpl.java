package com.gescov.webserver.dao.chatPreview;

import org.springframework.stereotype.Repository;

@Repository("chatPreviewMongo")
public class ChatPreviewDaoImpl<T, ID> implements ChatPreviewDaoCustom<T,ID> {
}
