package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.GameAddDTO;
import com.michalkaluzinski.fantasyleague.entities.Game;

@Component
public class GameAddDTOToGameConverter implements Converter<GameAddDTO, Game> {

  @Override
  public Game convert(GameAddDTO source) {
    Game game = new Game();
    game.setHomeTeamScore(source.getHomeTeamScore());
    game.setAwayTeamScore(source.getAwayTeamScore());
    game.setRound(source.getRound());
    game.setStartAt(source.getStartAt());
    return game;
  }
}
