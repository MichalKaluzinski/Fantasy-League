package com.michalkaluzinski.fantasyleague.services;

import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;

public interface EmailService {
  public void sendRegistrationVerificationEmail(String addressTo, String token) throws RestApiException;
}
