package com.michalkaluzinski.fantasyleague.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.entities.User;
import com.michalkaluzinski.fantasyleague.model.UserDetailsImpl;
import com.michalkaluzinski.fantasyleague.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByLogin(username);
    if (!user.isPresent()) {
      throw new UsernameNotFoundException("User not found.");
    }
    return new UserDetailsImpl(user.get());
  }
}
