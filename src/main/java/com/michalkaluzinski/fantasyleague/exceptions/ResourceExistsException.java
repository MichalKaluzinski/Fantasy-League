package com.michalkaluzinski.fantasyleague.exceptions;

public class ResourceExistsException extends RestApiException {

  private static final long serialVersionUID = 1L;

  public ResourceExistsException() {
    super();
  }

  public ResourceExistsException(String message) {
    super(message);
  }

  public ResourceExistsException(String message, Throwable exception) {
    super(message, exception);
  }
}
