package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.LeagueAddDTO;
import com.michalkaluzinski.fantasyleague.entities.League;

@Component
public class LeagueAddDTOToLeagueConverter implements Converter<LeagueAddDTO, League> {

  @Override
  public League convert(LeagueAddDTO source) {
    League league = new League();
    league.setName(source.getName());
    return league;
  }
}
