package com.michalkaluzinski.fantasyleague.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.michalkaluzinski.fantasyleague.model.User;
import com.michalkaluzinski.fantasyleague.model.VerificationToken;
import com.michalkaluzinski.fantasyleague.repositories.UserRepository;
import com.michalkaluzinski.fantasyleague.repositories.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private VerificationTokenRepository verificationTokenRepository;

  @Autowired private EmailService emailService;
  
  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public List<User> findAll() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  @Override
  @Transactional(rollbackFor = IOException.class)
  public void register(User user) throws IOException {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    VerificationToken verificationToken = new VerificationToken();
    verificationToken.setUserId(savedUser.getId());
    verificationToken.setToken(UUID.randomUUID().toString());
    verificationToken.setExpirationDate(new Timestamp(System.currentTimeMillis() + 3600000));
    verificationTokenRepository.save(verificationToken);
    emailService.sendRegistrationVerificationEmail(user.getEmail(), verificationToken.getToken());
  }

  @Override
  public void confirmRegistration(String token) {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
    if(verificationToken != null) {
      Optional<User> userOptional = userRepository.findById(verificationToken.getUserId());
      if(userOptional.isPresent()) {
        User user = userOptional.get();
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.delete(verificationToken);
      }
      
    }
    
  }
}
