package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import com.michalkaluzinski.fantasyleague.dtos.UserPredicateDTO;

public interface UserPredicateService {
  
  public List<UserPredicateDTO> findAll();
}
