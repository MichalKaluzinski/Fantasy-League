package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.TeamDTO;
import com.michalkaluzinski.fantasyleague.entities.Team;

@Component
public class TeamToTeamDTOConverter implements Converter<Team, TeamDTO> {

  @Override
  public TeamDTO convert(Team source) {
    TeamDTO teamDTO = new TeamDTO();
    teamDTO.setId(source.getId());
    teamDTO.setName(source.getName());
    teamDTO.setLeagueId(source.getLeague().getId());
    return teamDTO;
  }
}
