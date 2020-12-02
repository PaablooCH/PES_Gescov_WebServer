package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotInSchool extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotInSchool (String userID, String schoolID) {
        super("User with 'id' " + userID + " was not found in school with 'id' " + schoolID + "!");
    }
}
