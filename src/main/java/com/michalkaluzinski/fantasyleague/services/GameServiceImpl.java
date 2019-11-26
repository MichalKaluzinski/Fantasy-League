package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.GameToGameDTOConverter;
import com.michalkaluzinski.fantasyleague.dtos.GameDTO;
import com.michalkaluzinski.fantasyleague.repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {

  @Autowired private GameRepository gameRepository;

  @Autowired private GameToGameDTOConverter gameToGameDTOConverter;

  @Override
  public List<GameDTO> findAll() {

    return gameRepository
        .findAll()
        .stream()
        .map(game -> gameToGameDTOConverter.convert(game))
        .collect(Collectors.toList());
  }
}
