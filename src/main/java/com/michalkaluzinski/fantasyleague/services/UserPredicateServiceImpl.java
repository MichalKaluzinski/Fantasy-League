package com.michalkaluzinski.fantasyleague.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.michalkaluzinski.fantasyleague.converters.UserPredicateToUserDTOPredicateConverter;
import com.michalkaluzinski.fantasyleague.dtos.UserPredicateDTO;
import com.michalkaluzinski.fantasyleague.repositories.UserPredicateRepository;

@Service
public class UserPredicateServiceImpl implements UserPredicateService {

  @Autowired private UserPredicateRepository userPredicateRepository;

  @Autowired
  private UserPredicateToUserDTOPredicateConverter userPredicateToUserDTOPredicateConverter;

  @Override
  public List<UserPredicateDTO> findAll() {
    return userPredicateRepository
        .findAll()
        .stream()
        .map(userPredicate -> userPredicateToUserDTOPredicateConverter.convert(userPredicate))
        .collect(Collectors.toList());
  }
}
