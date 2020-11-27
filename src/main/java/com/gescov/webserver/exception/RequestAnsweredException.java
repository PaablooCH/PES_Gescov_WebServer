package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RequestAnsweredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RequestAnsweredException(Class c, String id) {
        super(c.getSimpleName() + " with 'id' " + id + " is already answered!");
    }
}
