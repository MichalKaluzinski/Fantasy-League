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
import com.michalkaluzinski.fantasyleague.converters.UserRegistrationDTOToUserConverter;
import com.michalkaluzinski.fantasyleague.converters.UserToUserDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.UserDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserLoginDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserRegistrationDTO;
import com.michalkaluzinski.fantasyleague.entities.User;
import com.michalkaluzinski.fantasyleague.entities.VerificationToken;
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

  @Autowired private UserToUserDTOConverter userToUserDTOConverter;

  @Autowired private UserRegistrationDTOToUserConverter userRegistrationDTOToUserConverter;

  @Value("${jwt.secret.key}")
  private String secretKey;

  @Override
  public List<UserDTO> findAll() {
    List<UserDTO> userDTOs = new ArrayList<>();
    userRepository.findAll().forEach(user -> userDTOs.add(userToUserDTOConverter.convert(user)));
    return userDTOs;
  }

  @Override
  @Transactional(rollbackFor = IOException.class)
  public void register(UserRegistrationDTO userRegistrationDTO) throws IOException {
    User user = userRegistrationDTOToUserConverter.convert(userRegistrationDTO);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user = userRepository.save(user);
    VerificationToken verificationToken = new VerificationToken();
    verificationToken.setUserId(user.getId());
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
  public String login(UserLoginDTO userLoginDTO) {
    Optional<User> userOptional = userRepository.findByLogin(userLoginDTO.getLogin());
    String token = null;
    if (userOptional.isPresent()
        && passwordEncoder.matches(userLoginDTO.getPassword(), userOptional.get().getPassword())) {
      token =
          Jwts.builder()
              .setSubject(userLoginDTO.getLogin())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 600000))
              .signWith(SignatureAlgorithm.HS512, secretKey)
              .compact();
    }
    return token;
  }
}
