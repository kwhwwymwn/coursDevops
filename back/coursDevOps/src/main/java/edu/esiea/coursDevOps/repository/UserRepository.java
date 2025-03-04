package edu.esiea.coursDevOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.coursDevOps.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
