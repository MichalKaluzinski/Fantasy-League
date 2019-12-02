package com.michalkaluzinski.fantasyleague.dtos;

import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO extends UserLoginDTO {

  @Email(message = "Email should be valid.")
  private String email;
}
