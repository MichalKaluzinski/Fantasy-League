package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.model.User;

public interface UserService {

  public List<User> findAll();
}
