package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.CustomLeagueDTO;

public interface CustomLeagueService {
  
  public List<CustomLeagueDTO> findAll();
}
