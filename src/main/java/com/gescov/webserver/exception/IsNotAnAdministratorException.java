package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IsNotAnAdministratorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IsNotAnAdministratorException (Class c, String id) { super(c.getSimpleName() + " 'id' " + id + " was not found!"); }
}
