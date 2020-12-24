package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClassroomInUseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ClassroomInUseException(String id, LocalDate date, LocalTime hour, LocalTime finishHour) {
        super("Classroom with 'id' " + id + " will be in use at those " + hour + " " + finishHour + " hours in " + date + "!");
    }
}
