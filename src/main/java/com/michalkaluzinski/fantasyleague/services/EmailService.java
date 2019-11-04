package com.michalkaluzinski.fantasyleague.services;

import java.io.IOException;

public interface EmailService {
  public void sendRegistrationVerificationEmail(String addressTo, String token) throws IOException;
}
