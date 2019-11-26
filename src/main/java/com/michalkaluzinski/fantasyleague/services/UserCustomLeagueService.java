package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.UserCustomLeagueDTO;

public interface UserCustomLeagueService {

  public List<UserCustomLeagueDTO> findAll();
}
