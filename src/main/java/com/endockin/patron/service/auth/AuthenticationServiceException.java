package com.endockin.patron.service.auth;

import com.endockin.patron.service.PatronServiceException;

public class AuthenticationServiceException extends PatronServiceException {

    public AuthenticationServiceException(String message) {
        super(message);
    }

}
