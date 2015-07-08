package com.endockin.patron.service.blueprint;

import com.endockin.patron.service.PatronServiceException;

public class BlueprintServiceException extends PatronServiceException {

  /**
   * 
   */
  private static final long serialVersionUID = 6157760961883018409L;

  public enum Type {

    NOT_FOUND, ALREADY_EXISTS, OTHER
  }

  private final Type type;

  public BlueprintServiceException(String message, Type type) {
    super(message);
    this.type = type;
  }

  public Type getType() {
    return type;
  }

}
