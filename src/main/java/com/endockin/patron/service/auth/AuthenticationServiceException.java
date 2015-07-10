package com.endockin.patron.service.auth;

import com.endockin.patron.service.PatronServiceException;

public class AuthenticationServiceException extends PatronServiceException {

  /**
   * 
   */
  private static final long serialVersionUID = 4255879275257486489L;

  public AuthenticationServiceException(String message) {
    super(message);
  }

}
