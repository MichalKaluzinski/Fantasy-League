package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.TeamToTeamDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.TeamDTO;
import com.michalkaluzinski.fantasyleague.repositories.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

  @Autowired private TeamRepository teamRepository;

  @Autowired private TeamToTeamDTOConverter teamToTeamDTOConverter;

  @Override
  public List<TeamDTO> findAll() {
    return teamRepository
        .findAll()
        .stream()
        .map(team -> teamToTeamDTOConverter.convert(team))
        .collect(Collectors.toList());
  }
}
