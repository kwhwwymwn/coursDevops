package edu.esiea.coursDevOps.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private final UserRepository repo;
	
	public UserService(UserRepository repo) {
		this.repo = repo;
	}
	
	public List<User> getAll(){
		return repo.findAll();
	}
	
	public User get(int index) {
		return repo.findById(index).orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	public User get(String login){
		return repo.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found"));
	}
}
