package com.gescov.webserver.exception;

import com.gescov.webserver.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CreatorCantBeDeletedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CreatorCantBeDeletedException(Class<User> c, String creatorID) {
        super(c.getSimpleName() + " 'id' " + creatorID + " can't be deleted as an administrator as it's the creator");
    }

}
