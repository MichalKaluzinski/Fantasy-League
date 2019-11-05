package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.UserDTO;
import com.michalkaluzinski.fantasyleague.entities.User;

@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO>{

  @Override
  public UserDTO convert(User source) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(source.getId());
    userDTO.setLogin(source.getLogin());
    userDTO.setEmail(source.getEmail());
    return userDTO;
  }}
