package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.UserCustomLeagueToUserCustomLeagueDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.UserCustomLeagueDTO;
import com.michalkaluzinski.fantasyleague.repositories.UserCustomLeagueRepository;

@Service
public class UserCustomLeagueServiceImpl implements UserCustomLeagueService {

  @Autowired private UserCustomLeagueRepository userCustomLeagueRepository;

  @Autowired
  private UserCustomLeagueToUserCustomLeagueDTOConverter
      userCustomLeagueToUserCustomLeagueDTOConverter;

  @Override
  public List<UserCustomLeagueDTO> findAll() {
    return userCustomLeagueRepository
        .findAll()
        .stream()
        .map(
            userCustomLeague ->
                userCustomLeagueToUserCustomLeagueDTOConverter.convert(userCustomLeague))
        .collect(Collectors.toList());
  }
}
