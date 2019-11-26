package com.michalkaluzinski.fantasyleague.dtos;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class UserCustomLeagueDTO {

  private Integer userId;

  private Integer customLeagueId;

  private Integer membershipType;
}
