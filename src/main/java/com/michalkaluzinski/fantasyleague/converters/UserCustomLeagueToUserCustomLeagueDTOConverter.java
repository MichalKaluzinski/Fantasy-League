package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.UserCustomLeagueDTO;
import com.michalkaluzinski.fantasyleague.entities.UserCustomLeague;

@Component
public class UserCustomLeagueToUserCustomLeagueDTOConverter
    implements Converter<UserCustomLeague, UserCustomLeagueDTO> {

  @Override
  public UserCustomLeagueDTO convert(UserCustomLeague source) {
    UserCustomLeagueDTO userCustomLeagueDTO = new UserCustomLeagueDTO();
    userCustomLeagueDTO.setUserId(source.getUser().getId());
    userCustomLeagueDTO.setCustomLeagueId(source.getCustomLeague().getId());
    userCustomLeagueDTO.setMembershipType(source.getMembershipType());
    return userCustomLeagueDTO;
  }
}
