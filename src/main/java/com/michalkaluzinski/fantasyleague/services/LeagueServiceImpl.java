package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.LeagueAddDTOToLeagueConverter;
import com.michalkaluzinski.fantasyleague.converters.LeagueToLeagueDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.LeagueAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.LeagueDTO;
import com.michalkaluzinski.fantasyleague.entities.League;
import com.michalkaluzinski.fantasyleague.exceptions.ResourceExistsException;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;
import com.michalkaluzinski.fantasyleague.repositories.LeagueRepository;

@Service
public class LeagueServiceImpl implements LeagueService {

  @Autowired private LeagueRepository leagueRepository;

  @Autowired private LeagueToLeagueDTOConverter leagueToLeagueDTOConverter;

  @Autowired private LeagueAddDTOToLeagueConverter leagueAddDTOToLeagueConverter;

  @Override
  public List<LeagueDTO> findAll() {
    return leagueRepository
        .findAll()
        .stream()
        .map(n -> leagueToLeagueDTOConverter.convert(n))
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public LeagueDTO add(LeagueAddDTO leagueAddDTO) throws RestApiException {
    if (leagueRepository.findByName(leagueAddDTO.getName()).isPresent()) {
      throw new ResourceExistsException(
          String.format("League with name: %s exists.", leagueAddDTO.getName()));
    }
    League leagueAdded = leagueRepository.save(leagueAddDTOToLeagueConverter.convert(leagueAddDTO));
    return leagueToLeagueDTOConverter.convert(leagueAdded);
  }

  @Override
  public void delete(Integer leagueId) {
    leagueRepository.deleteById(leagueId);
  }
}
