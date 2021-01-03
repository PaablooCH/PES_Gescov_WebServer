package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UAreInfected extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UAreInfected(String id) {
        super("User with 'id' " + id + " is infected!");
    }
}
