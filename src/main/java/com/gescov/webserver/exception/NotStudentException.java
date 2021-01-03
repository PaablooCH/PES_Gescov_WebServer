package com.gescov.webserver.exception;

import com.gescov.webserver.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotStudentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotStudentException(Class<User> c, String userID) {
        super(c.getSimpleName() + " 'id' " + userID + " is a teacher!");
    }
}
