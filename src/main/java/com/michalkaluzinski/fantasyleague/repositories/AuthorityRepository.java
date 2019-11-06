package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
  Authority findByName(String name);
}
