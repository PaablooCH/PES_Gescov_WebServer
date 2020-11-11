package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlaceOutOfIndexException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PlaceOutOfIndexException(Integer numRow, Integer numCol) {
        super("Selected place is not eligible, it must be less or equal than " + numRow + " and " + numCol + ".");
    }
}

