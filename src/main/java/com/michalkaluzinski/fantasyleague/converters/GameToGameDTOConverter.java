package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.GameDTO;
import com.michalkaluzinski.fantasyleague.entities.Game;

@Component
public class GameToGameDTOConverter implements Converter<Game, GameDTO> {

  @Override
  public GameDTO convert(Game source) {
    GameDTO gameDTO = new GameDTO();
    gameDTO.setId(source.getId());
    gameDTO.setLeagueId(source.getLeague().getId());
    gameDTO.setHomeTeamId(source.getHomeTeam().getId());
    gameDTO.setAwayTeamId(source.getAwayTeam().getId());
    gameDTO.setRound(source.getRound());
    gameDTO.setHomeTeamScore(source.getHomeTeamScore());
    gameDTO.setAwayTeamScore(source.getAwayTeamScore());
    gameDTO.setStartAt(source.getStartAt());
    return gameDTO;
  }
}
