package com.michalkaluzinski.fantasyleague.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.michalkaluzinski.fantasyleague.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
  public Optional<User> findByLogin(String login);

  public Optional<User> findByEmail(String email);
}
