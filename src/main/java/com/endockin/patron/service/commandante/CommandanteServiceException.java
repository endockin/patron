package com.endockin.patron.service.commandante;

import com.endockin.patron.service.PatronServiceException;

public class CommandanteServiceException extends PatronServiceException {

  /**
   * 
   */
  private static final long serialVersionUID = 3134897608824035086L;

  public enum Type {

    NOT_FOUND, ALREADY_EXISTS, OTHER
  }

  private final Type type;

  public CommandanteServiceException(String message, Type type) {
    super(message);
    this.type = type;
  }

  public Type getType() {
    return type;
  }

}
