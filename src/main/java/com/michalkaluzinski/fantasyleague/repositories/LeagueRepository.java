package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.League;

public interface LeagueRepository extends JpaRepository<League, Integer>{}
