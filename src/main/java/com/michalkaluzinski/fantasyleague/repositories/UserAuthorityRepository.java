package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.UserAuthority;
import com.michalkaluzinski.fantasyleague.entities.UserAuthorityPK;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityPK> {}
