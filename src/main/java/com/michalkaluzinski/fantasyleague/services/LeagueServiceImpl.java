package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.LeagueToLeagueDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.LeagueDTO;
import com.michalkaluzinski.fantasyleague.repositories.LeagueRepository;

@Service
public class LeagueServiceImpl implements LeagueService {

  @Autowired private LeagueRepository leagueRepository;

  @Autowired private LeagueToLeagueDTOConverter leagueToLeagueDTOConverter;

  @Override
  public List<LeagueDTO> findAll() {
    return leagueRepository
        .findAll()
        .stream()
        .map(n -> leagueToLeagueDTOConverter.convert(n))
        .collect(Collectors.toList());
  }
}
