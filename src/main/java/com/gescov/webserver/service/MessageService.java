package com.gescov.webserver.service;

import com.gescov.webserver.dao.message.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageDao messageDao;

    @Autowired
    UserService userService;
}
