package edu.esiea.coursDevOps.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.services.UserService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/all")
	public List<User> getMethodName() {
		return service.getAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getMethodName(@PathVariable int id) {
        return ResponseEntity.ok(service.get(id));
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> postMethodName(@RequestBody User user) {
		User TrueUser = service.get(user.getLogin());
		if (TrueUser.getPassword().equals(user.getPassword()))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(400).build(); //Bad Request
	}

	@PostMapping("/create-default")
	public ResponseEntity<User> createDefaultUser() {
		User createdUser = service.createDefaultUser();
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}


}
