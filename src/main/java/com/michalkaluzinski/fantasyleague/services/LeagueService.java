package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.LeagueDTO;

public interface LeagueService {

  List<LeagueDTO> findAll();
}
