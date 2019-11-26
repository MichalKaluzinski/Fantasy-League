package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.UserCustomLeague;
import com.michalkaluzinski.fantasyleague.entities.UserCustomLeaguePK;

public interface UserCustomLeagueRepository
    extends JpaRepository<UserCustomLeague, UserCustomLeaguePK> {}
