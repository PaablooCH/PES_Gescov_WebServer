package com.gescov.webserver.exception;

import com.gescov.webserver.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotTeacherException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotTeacherException(Class<User> c, String userID) {
        super(c.getSimpleName() + " 'id' " + userID + " is a student!");
    }
}
