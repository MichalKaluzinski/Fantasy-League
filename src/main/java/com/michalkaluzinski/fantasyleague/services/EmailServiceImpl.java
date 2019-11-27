package com.michalkaluzinski.fantasyleague.services;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.exceptions.EmailException;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailServiceImpl implements EmailService {

  @Autowired private SendGrid sendGrid;

  @Override
  public void sendRegistrationVerificationEmail(String addressTo, String token)
      throws RestApiException {
    Email from = new Email("test@example.com");
    String subject = "Sending with SendGrid is Fun";
    Email to = new Email(addressTo);
    Content content = new Content("text/html", "Click to confirm registration:");
    Mail mail = new Mail(from, subject, to, content);
    mail.setTemplateId("3f79e148-a65b-45d8-8560-e46aca9bec75");
    mail.personalization.get(0).addSubstitution("-token-", token);
    Request request = new Request();
    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");
    try {
      request.setBody(mail.build());
      sendGrid.api(request);
    } catch (IOException e) {
      throw new EmailException("Unable to send verification mail.");
    }
  }
}
