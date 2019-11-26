package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {}
