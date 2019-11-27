package com.michalkaluzinski.fantasyleague.exceptions;

public class EmailException extends RestApiException {

  private static final long serialVersionUID = 1L;

  public EmailException() {
    super();
  }

  public EmailException(String message) {
    super(message);
  }

  public EmailException(String message, Throwable exception) {
    super(message, exception);
  }
}
