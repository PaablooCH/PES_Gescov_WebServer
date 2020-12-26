package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotAnyTextException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotAnyTextException() {
        super("This entry doesn't have any text");
    }

}
