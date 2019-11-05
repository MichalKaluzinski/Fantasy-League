package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.UserRegistrationDTO;
import com.michalkaluzinski.fantasyleague.entities.User;

@Component
public class UserRegistrationDTOToUserConverter implements Converter<UserRegistrationDTO, User> {

  @Override
  public User convert(UserRegistrationDTO source) {
    User user = new User();
    user.setEmail(source.getEmail());
    user.setLogin(source.getLogin());
    user.setPassword(source.getPassword());
    return user;
  }
}
