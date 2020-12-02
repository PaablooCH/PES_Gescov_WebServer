package com.gescov.webserver.dao.chatPreview;

import jdk.jfr.Registered;
import org.springframework.stereotype.Repository;

@Repository("chatPreviewMongo")
public class ChatPreviewDaoImpl<T, ID> implements ChatPreviewDaoCustom<T,ID> {
}
