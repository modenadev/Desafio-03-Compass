package io.github.modenadev.userproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidUsernamePasswordException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public InvalidUsernamePasswordException(String ex) {
        super(ex);
    }
}