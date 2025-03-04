package edu.esiea.coursDevOps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public List<User> getMethodName() {
		return service.getAll();
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<User> getMethodName(@PathVariable int id) {
        return ResponseEntity.ok(service.get(id));
	}

	
	//TODO login
}
