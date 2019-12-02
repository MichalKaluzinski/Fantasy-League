package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.GameAddDTO;
import com.michalkaluzinski.fantasyleague.dtos.GameDTO;
import com.michalkaluzinski.fantasyleague.exceptions.RestApiException;

public interface GameService {

  public List<GameDTO> findAll();

  public GameDTO add(GameAddDTO gameAddDTO) throws RestApiException;
}
