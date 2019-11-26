package com.michalkaluzinski.fantasyleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.michalkaluzinski.fantasyleague.entities.UserPredicate;
import com.michalkaluzinski.fantasyleague.entities.UserPredicatePK;

public interface UserPredicateRepository extends JpaRepository<UserPredicate, UserPredicatePK>{}
