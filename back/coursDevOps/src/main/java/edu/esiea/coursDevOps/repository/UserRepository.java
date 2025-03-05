package edu.esiea.coursDevOps.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.coursDevOps.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByLogin(String login);
}
