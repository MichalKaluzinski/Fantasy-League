package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.TeamDTO;

public interface TeamService {

  public List<TeamDTO> findAll();
  
}
