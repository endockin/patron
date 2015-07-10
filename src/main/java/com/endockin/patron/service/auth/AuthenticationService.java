package com.endockin.patron.service.auth;

import com.endockin.patron.model.Authentication;
import com.endockin.patron.model.User;

public interface AuthenticationService {

    Authentication authenticate(User u) throws AuthenticationServiceException;

    void deauthenticate(String key) throws AuthenticationServiceException;

    Authentication isAuthenticated(String key) throws AuthenticationServiceException;
}
