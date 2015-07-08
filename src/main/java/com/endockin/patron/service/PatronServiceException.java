package com.endockin.patron.service;

public abstract class PatronServiceException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 8134117223302527497L;

  public PatronServiceException(String message) {
    super(message);
  }

}
