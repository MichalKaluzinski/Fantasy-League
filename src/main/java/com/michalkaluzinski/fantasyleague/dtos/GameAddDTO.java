package com.michalkaluzinski.fantasyleague.dtos;

import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameAddDTO {

  @NotNull
  private Integer leagueId;

  @NotNull
  private Integer homeTeamId;

  @NotNull
  private Integer awayTeamId;

  @NotNull
  private Integer round;

  private Integer homeTeamScore;

  private Integer awayTeamScore;

  private Timestamp startAt;
}
