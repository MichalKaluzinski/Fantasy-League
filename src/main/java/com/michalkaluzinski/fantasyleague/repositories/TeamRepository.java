package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {}
