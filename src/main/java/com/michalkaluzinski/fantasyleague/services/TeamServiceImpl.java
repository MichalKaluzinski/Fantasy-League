package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.TeamAddDTOToTeamConverter;
import com.michalkaluzinski.fantasyleague.converters.TeamToTeamDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.TeamAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.TeamDTO;
import com.michalkaluzinski.fantasyleague.entities.League;
import com.michalkaluzinski.fantasyleague.entities.Team;
import com.michalkaluzinski.fantasyleague.exceptions.NotFoundException;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
import com.michalkaluzinski.fantasyleague.repositories.LeagueRepository;
import com.michalkaluzinski.fantasyleague.repositories.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

  @Autowired private TeamRepository teamRepository;

  @Autowired private LeagueRepository leagueRepository;

  @Autowired private TeamToTeamDTOConverter teamToTeamDTOConverter;

  @Autowired private TeamAddDTOToTeamConverter teamAddDTOToTeamConverter;

  @Override
  public List<TeamDTO> findAll() {
    return teamRepository
        .findAll()
        .stream()
        .map(team -> teamToTeamDTOConverter.convert(team))
        .collect(Collectors.toList());
  }

  @Override
  public TeamDTO add(TeamAddDTO teamAddDTO) throws RestApiException {
    Optional<League> league = leagueRepository.findById(teamAddDTO.getLeagueId());
    if (!league.isPresent()) {
      throw new NotFoundException(
          String.format("League with id %d not exists.", teamAddDTO.getLeagueId()));
    }
    Team team = teamAddDTOToTeamConverter.convert(teamAddDTO);
    team.setLeague(league.get());
    Team teamAdded = teamRepository.save(team);
    return teamToTeamDTOConverter.convert(teamAdded);
  }
}
