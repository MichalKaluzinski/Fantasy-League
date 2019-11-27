package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.TeamAddDTO;
import com.michalkaluzinski.fantasyleague.entities.Team;

@Component
public class TeamAddDTOToTeamConverter implements Converter<TeamAddDTO, Team> {

  @Override
  public Team convert(TeamAddDTO source) {

    Team team = new Team();
    team.setName(source.getName());
    return team;
  }
}
