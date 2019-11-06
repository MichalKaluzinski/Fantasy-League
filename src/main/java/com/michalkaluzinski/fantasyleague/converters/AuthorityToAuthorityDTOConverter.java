package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.AuthorityDTO;
import com.michalkaluzinski.fantasyleague.entities.Authority;

@Component
public class AuthorityToAuthorityDTOConverter implements Converter<Authority, AuthorityDTO>{

  @Override
  public AuthorityDTO convert(Authority source) {
    AuthorityDTO authorityDTO = new AuthorityDTO();
    authorityDTO.setId(source.getId());
    authorityDTO.setName(source.getName());
    return authorityDTO;
  }}
