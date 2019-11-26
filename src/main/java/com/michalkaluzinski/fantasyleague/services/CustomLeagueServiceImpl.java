package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.CustomLeagueToCustomLeagueDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.CustomLeagueDTO;
import com.michalkaluzinski.fantasyleague.repositories.CustomLeagueRepository;

@Service
public class CustomLeagueServiceImpl implements CustomLeagueService {

  @Autowired private CustomLeagueRepository customLeagueRepository;

  @Autowired private CustomLeagueToCustomLeagueDTOConverter customLeagueToCustomLeagueDTOConverter;

  @Override
  public List<CustomLeagueDTO> findAll() {
    return customLeagueRepository
        .findAll()
        .stream()
        .map(customLeague -> customLeagueToCustomLeagueDTOConverter.convert(customLeague))
        .collect(Collectors.toList());
  }
}
