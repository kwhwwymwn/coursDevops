package edu.esiea.coursDevOps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public List<User> getAll(){
		return repo.findAll();
	}
	
	public User get(int index) {
		return repo.findById(index).orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	public User get(String login){
		return repo.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found"));
	}

    @Transactional
    public User createDefaultUser() {
        // Check if the user with ID 11 already exists
        Optional<User> existingUser = repo.findById(11);

        if (existingUser.isPresent()) {
            // User already exists, you might want to update it instead,
            // or return the existing user.  For this example, we'll just return it.
            return existingUser.get();
        } else {
            // User does not exist, create a new one
            User defaultUser = new User();
            defaultUser.setId(11);
            defaultUser.setLogin("default");
            defaultUser.setPassword("password123");
            // Set any other default fields as needed

            return repo.save(defaultUser);
        }
    }


}
