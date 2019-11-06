package com.michalkaluzinski.fantasyleague.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.michalkaluzinski.fantasyleague.converters.UserRegistrationDTOToUserConverter;
import com.michalkaluzinski.fantasyleague.converters.UserToUserDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.UserDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserLoginDTO;
import com.michalkaluzinski.fantasyleague.dtos.UserRegistrationDTO;
import com.michalkaluzinski.fantasyleague.entities.Authority;
import com.michalkaluzinski.fantasyleague.entities.User;
import com.michalkaluzinski.fantasyleague.entities.UserAuthority;
import com.michalkaluzinski.fantasyleague.entities.UserAuthorityPK;
import com.michalkaluzinski.fantasyleague.entities.VerificationToken;
import com.michalkaluzinski.fantasyleague.repositories.AuthorityRepository;
import com.michalkaluzinski.fantasyleague.repositories.UserAuthorityRepository;
import com.michalkaluzinski.fantasyleague.repositories.UserRepository;
import com.michalkaluzinski.fantasyleague.repositories.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private VerificationTokenRepository verificationTokenRepository;

  @Autowired private AuthorityRepository authorityRepository;

  @Autowired private UserAuthorityRepository userAuthorityRepository;

  @Autowired private EmailService emailService;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private UserToUserDTOConverter userToUserDTOConverter;

  @Autowired private UserRegistrationDTOToUserConverter userRegistrationDTOToUserConverter;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtTokenProviderService jwtTokenProviderService;

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
    UserAuthorityPK userAuthorityPK = new UserAuthorityPK();
    userAuthorityPK.setAuthorityId(authorityRepository.findByName("ROLE_USER").getId());
    userAuthorityPK.setUserId(user.getId());
    UserAuthority userAuthority = new UserAuthority();
    userAuthority.setUserAuthorityPK(userAuthorityPK);
    userAuthorityRepository.save(userAuthority);
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
    String username = userLoginDTO.getLogin();
    String password = userLoginDTO.getPassword();
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      String token =
          jwtTokenProviderService.createToken(
              username,
              new HashSet<String>(
                  userRepository
                      .findByLogin(username)
                      .get()
                      .getUserAuthorities()
                      .stream()
                      .map(UserAuthority::getAuthority)
                      .map(Authority::getName)
                      .collect(Collectors.toList())));
      return "Bearer " + token;
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username/password supplied");
    }
  }
}
