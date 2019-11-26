package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.GameDTO;

public interface GameService {
  
  public List<GameDTO> findAll();
}
