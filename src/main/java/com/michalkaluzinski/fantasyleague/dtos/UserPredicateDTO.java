package com.michalkaluzinski.fantasyleague.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPredicateDTO {
  
  private Integer userId;
  
  private Integer gameId;
  
  private Integer homeTeamScorePredicate;
  
  private Integer awayTeamScorePredicate;
}
