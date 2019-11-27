package com.michalkaluzinski.fantasyleague.exceptions;

public class NotFoundException extends RestApiException {

  private static final long serialVersionUID = 1L;

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable exception) {
    super(message, exception);
  }
}
