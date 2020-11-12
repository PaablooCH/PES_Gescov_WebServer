package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClassroomDateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClassroomDateException(Class c, String id, String date) {
        super("Any " + c.getSimpleName() + " with classroomID " + id + " and Date " + date + " was found!");
    }
}
