package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.LeagueAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.LeagueDTO;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;

public interface LeagueService {

  List<LeagueDTO> findAll();

  LeagueDTO add(LeagueAddDTO leagueAddDTO) throws RestApiException;

  void delete(Integer leagueId) throws RestApiException;
}
