package com.michalkaluzinski.fantasyleague.converters;

import java.util.HashSet;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.UserDTO;
import com.michalkaluzinski.fantasyleague.entities.User;
import com.michalkaluzinski.fantasyleague.entities.UserAuthority;

@Component
public class UserToUserDTOConverter implements Converter<User, UserDTO> {

  @Autowired private AuthorityToAuthorityDTOConverter authorityToAuthorityDTOConverter;

  @Override
  public UserDTO convert(User source) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(source.getId());
    userDTO.setLogin(source.getLogin());
    userDTO.setEmail(source.getEmail());
    userDTO.setAuthorities(
        new HashSet<>(
            source
                .getUserAuthorities()
                .stream()
                .map(UserAuthority::getAuthority)
                .map(e -> authorityToAuthorityDTOConverter.convert(e))
                .collect(Collectors.toList())));
    return userDTO;
  }
}
