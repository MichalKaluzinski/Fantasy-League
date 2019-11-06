package com.michalkaluzinski.fantasyleague;

public class InvalidJwtAuthenticationException extends Exception {

  private static final long serialVersionUID = 1L;

  public InvalidJwtAuthenticationException(String message) {
    super(message);
  }
}
