package com.michalkaluzinski.fantasyleague.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

  public Optional<Team> findByIdAndLeagueId(Integer id, Integer leagueId);
}
