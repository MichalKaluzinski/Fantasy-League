package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.CustomLeague;

public interface CustomLeagueRepository extends JpaRepository<CustomLeague, Integer> {}
