package com.michalkaluzinski.fantasyleague.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.michalkaluzinski.fantasyleague.dtos.UserPredicateDTO;
import com.michalkaluzinski.fantasyleague.entities.UserPredicate;

@Component
public class UserPredicateToUserDTOPredicateConverter implements Converter<UserPredicate, UserPredicateDTO>{

  @Override
  public UserPredicateDTO convert(UserPredicate source) {
    UserPredicateDTO userPredicateDTO = new UserPredicateDTO();
    userPredicateDTO.setUserId(source.getUserPredicatePK().getUserId());
    userPredicateDTO.setGameId(source.getUserPredicatePK().getGameId());
    userPredicateDTO.setHomeTeamScorePredicate(source.getHomeTeamScorePredicate());
    userPredicateDTO.setAwayTeamScorePredicate(source.getAwayTeamScorePredicate());
    return userPredicateDTO;
  }}
