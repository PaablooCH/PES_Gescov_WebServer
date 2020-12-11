package com.gescov.webserver.exception;

import com.gescov.webserver.model.Chat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChatAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ChatAlreadyExistsException(Class<Chat> c, String idA, String idB) {
        super(c.getSimpleName() + " between " + idA + " and " + idB + " already exists!");
    }
}
