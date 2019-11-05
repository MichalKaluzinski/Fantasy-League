package com.michalkaluzinski.fantasyleague.services;

import java.io.IOException;
import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.UserDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserLoginDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserRegistrationDTO;

public interface UserService {

  public List<UserDTO> findAll();

  public void register(UserRegistrationDTO userRegistrationDTO) throws IOException;

  public void confirmRegistration(String token);

  public String login(UserLoginDTO userLoginDTO);
}
