package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UNeedToWait extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UNeedToWait(String id) {
        super("User with 'id' " + id + " needs to wait until notify contagion again!");
    }
}
