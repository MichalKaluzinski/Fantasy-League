package com.michalkaluzinski.fantasyleague.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO extends UserLoginDTO {

  private String email;
}
