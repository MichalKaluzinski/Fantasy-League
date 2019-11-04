package com.michalkaluzinski.fantasyleague.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.model.User;
import com.michalkaluzinski.fantasyleague.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public List<User> findAll() {
    List<User> users = new ArrayList<User>();
    userRepository.findAll().forEach(users::add);
    return users;
  }
}
