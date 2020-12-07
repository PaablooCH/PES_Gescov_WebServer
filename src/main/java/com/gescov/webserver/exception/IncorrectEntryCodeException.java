package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncorrectEntryCodeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IncorrectEntryCodeException(String schoolID, String code) {
        super("The 'code' " + code + " does not match the code from the school with 'id' " + schoolID);
    }

}
