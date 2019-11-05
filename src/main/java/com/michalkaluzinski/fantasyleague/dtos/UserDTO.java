package com.michalkaluzinski.fantasyleague.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  
  private Integer id;
  private String login;
  private String email;
}
