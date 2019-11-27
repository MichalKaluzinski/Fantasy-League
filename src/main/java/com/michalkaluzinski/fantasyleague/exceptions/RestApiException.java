package com.michalkaluzinski.fantasyleague.exceptions;

public class RestApiException extends Exception {

  private static final long serialVersionUID = 1L;

  public RestApiException() {
    super();
  }

  public RestApiException(String message) {
    super(message);
  }

  public RestApiException(String message, Throwable exception) {
    super(message, exception);
  }
}
