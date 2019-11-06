package com.michalkaluzinski.fantasyleague.dtos;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
  
  private Integer id;
  private String login;
  private String email;
  private Set<AuthorityDTO> authorities;
}
