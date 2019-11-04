package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.repository.CrudRepository;
import com.michalkaluzinski.fantasyleague.model.VerificationToken;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Integer> {
  public VerificationToken findByToken(String token);
}
