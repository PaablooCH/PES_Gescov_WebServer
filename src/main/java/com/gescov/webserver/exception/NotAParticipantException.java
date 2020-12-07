package com.gescov.webserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAParticipantException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotAParticipantException(Class c, String creatorID, String chatID) {
        super(c.getSimpleName() + " 'id' " + creatorID + " is not a participant of the chat " + chatID);
    }

}
