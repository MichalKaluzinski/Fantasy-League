package com.michalkaluzinski.fantasyleague.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.michalkaluzinski.fantasyleague.model.User;
import com.michalkaluzinski.fantasyleague.model.VerificationToken;
import com.michalkaluzinski.fantasyleague.repositories.UserRepository;
import com.michalkaluzinski.fantasyleague.repositories.VerificationTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private VerificationTokenRepository verificationTokenRepository;

  @Autowired private EmailService emailService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Value("${jwt.secret.key}")
  private String secretKey;

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
    if (verificationToken != null) {
      Optional<User> userOptional = userRepository.findById(verificationToken.getUserId());
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.delete(verificationToken);
      }
    }
  }

  @Override
  public String login(User user) {
    Optional<User> userOptional = userRepository.findByLogin(user.getLogin());
    String token = null;
    if (userOptional.isPresent()
        && passwordEncoder.matches(user.getPassword(), userOptional.get().getPassword())) {
      token =
          Jwts.builder()
              .setSubject(user.getLogin())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 600000))
              .signWith(SignatureAlgorithm.HS512, secretKey)
              .compact();
    }
    return token;
  }
}
