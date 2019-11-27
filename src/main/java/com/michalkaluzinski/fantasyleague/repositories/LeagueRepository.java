package com.michalkaluzinski.fantasyleague.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.League;

public interface LeagueRepository extends JpaRepository<League, Integer> {

  Optional<League> findByName(String name);
}
