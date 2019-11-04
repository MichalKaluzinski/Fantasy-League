package com.michalkaluzinski.fantasyleague.services;

import java.io.IOException;
import java.util.List;
import com.michalkaluzinski.fantasyleague.model.User;

public interface UserService {

  public List<User> findAll();

  public void register(User user) throws IOException;
  
  public void confirmRegistration(String token);
}
