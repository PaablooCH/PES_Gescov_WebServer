package com.gescov.webserver.exception;

import com.gescov.webserver.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IsNotAnAdministratorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IsNotAnAdministratorException (Class<User> c, String adminID, String schoolID) {
        super(c.getSimpleName() + " 'id' " + adminID + " is not an administrator of the school " + schoolID);
    }
}
