package com.michalkaluzinski.fantasyleague.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.michalkaluzinski.fantasyleague.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

  @Query(
      value =
          "select * from game where round = :round AND (home_team_id = :teamId OR away_team_id = :teamId)",
      nativeQuery = true)
  public Optional<Game> findByHomeTeamIdOrAwayTeamIdAndRound(
      @Param("teamId") Integer teamId, @Param("round") Integer round);
}
