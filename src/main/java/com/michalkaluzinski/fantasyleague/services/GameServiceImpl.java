package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.GameAddDTOToGameConverter;
import com.michalkaluzinski.fantasyleague.converters.GameToGameDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.GameAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.GameDTO;
import com.michalkaluzinski.fantasyleague.entities.Game;
import com.michalkaluzinski.fantasyleague.entities.League;
import com.michalkaluzinski.fantasyleague.entities.Team;
import com.michalkaluzinski.fantasyleague.exceptions.ResourceExistsException;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
import com.michalkaluzinski.fantasyleague.repositories.GameRepository;
import com.michalkaluzinski.fantasyleague.repositories.LeagueRepository;
import com.michalkaluzinski.fantasyleague.repositories.TeamRepository;

@Service
public class GameServiceImpl implements GameService {

  @Autowired private GameRepository gameRepository;

  @Autowired private GameToGameDTOConverter gameToGameDTOConverter;

  @Autowired private LeagueRepository leagueRepository;

  @Autowired private TeamRepository teamRepository;

  @Autowired private GameAddDTOToGameConverter gameAddDTOToGameConverter;

  @Override
  public List<GameDTO> findAll() {

    return gameRepository
        .findAll()
        .stream()
        .map(game -> gameToGameDTOConverter.convert(game))
        .collect(Collectors.toList());
  }

  @Override
  public GameDTO add(GameAddDTO gameAddDTO) throws RestApiException {
    Game game = gameAddDTOToGameConverter.convert(gameAddDTO);
    Optional<League> leagueOptional = leagueRepository.findById(gameAddDTO.getLeagueId());
    if (!leagueOptional.isPresent()) {
      throw new ResourceExistsException(
          String.format("League with id = %d not exists.", gameAddDTO.getLeagueId()));
    }
    Optional<Team> homeTeamOptional =
        teamRepository.findByIdAndLeagueId(gameAddDTO.getHomeTeamId(), gameAddDTO.getLeagueId());
    if (!homeTeamOptional.isPresent()) {
      throw new ResourceExistsException(
          String.format(
              "Team with id = %d not exists for League with id = %d",
              gameAddDTO.getHomeTeamId(), gameAddDTO.getLeagueId()));
    }

    Optional<Team> awayTeamOptional =
        teamRepository.findByIdAndLeagueId(gameAddDTO.getAwayTeamId(), gameAddDTO.getLeagueId());

    if (!awayTeamOptional.isPresent()) {
      throw new ResourceExistsException(
          String.format(
              "Team with id = %d not exists for League with id = %d",
              gameAddDTO.getAwayTeamId(), gameAddDTO.getLeagueId()));
    }

    Optional<Game> homeTeamInRound =
        gameRepository.findByHomeTeamIdOrAwayTeamIdAndRound(
            gameAddDTO.getHomeTeamId(), gameAddDTO.getRound());
    if (homeTeamInRound.isPresent()) {
      throw new ResourceExistsException(
          String.format(
              "Team with id = %d has game in round = %d.",
              gameAddDTO.getHomeTeamId(), gameAddDTO.getRound()));
    }

    Optional<Game> awayTeamInRound =
        gameRepository.findByHomeTeamIdOrAwayTeamIdAndRound(
            gameAddDTO.getAwayTeamId(), gameAddDTO.getRound());
    if (awayTeamInRound.isPresent()) {
      throw new ResourceExistsException(
          String.format(
              "Team with id = %d has game in round = %d.",
              gameAddDTO.getAwayTeamId(), gameAddDTO.getRound()));
    }
    game.setLeague(leagueOptional.get());
    game.setHomeTeam(homeTeamOptional.get());
    game.setAwayTeam(awayTeamOptional.get());

    return gameToGameDTOConverter.convert(gameRepository.save(game));
  }
}
