package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.repository.CrudRepository;
import com.michalkaluzinski.fantasyleague.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {}
