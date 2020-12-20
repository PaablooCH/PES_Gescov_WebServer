package com.gescov.webserver.exception;

import com.gescov.webserver.model.School;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotEqualsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotEqualsException(Class c, String id1, String id2) {
        super(id1 + " and " + id2 + "are not the same " + c.getSimpleName() + "!");
    }
}
