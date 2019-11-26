package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.LeagueDTO;
import com.michalkaluzinski.fantasyleague.entities.League;

@Component
public class LeagueToLeagueDTOConverter implements Converter<League, LeagueDTO> {

  @Override
  public LeagueDTO convert(League source) {
    LeagueDTO leagueDTO = new LeagueDTO();
    leagueDTO.setId(source.getId());
    leagueDTO.setName(source.getName());
    leagueDTO.setActive(source.isActive());
    return leagueDTO;
  }
}
