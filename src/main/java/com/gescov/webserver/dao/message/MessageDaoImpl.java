package com.gescov.webserver.dao.message;

import org.springframework.stereotype.Repository;

@Repository("messageMongo")
public class MessageDaoImpl<T,ID> implements MessageDaoCustom<T,ID> {

}
