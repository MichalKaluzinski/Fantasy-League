package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.TeamAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.TeamDTO;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;

public interface TeamService {

  public List<TeamDTO> findAll();
  
  public TeamDTO add(TeamAddDTO teamAddDTO) throws RestApiException;
}
