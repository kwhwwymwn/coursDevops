package edu.esiea.coursDevOps.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository repo;
	
	@InjectMocks
	private UserService service;
	
	@Test
	void testGetOne() {
		User mockedUser = new User(1,"login", "password");
		
		// Simuler le comportement du repository
        when(repo.findById(1)).thenReturn(Optional.of(mockedUser));

        // Exécuter la méthode à tester
        User user = service.get(1);

        // Vérifier que le résultat est correct
        assertNotNull(user);
        assertEquals(mockedUser.getLogin(), user.getLogin());
        //TODO

        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).findById(1);
	}
}
