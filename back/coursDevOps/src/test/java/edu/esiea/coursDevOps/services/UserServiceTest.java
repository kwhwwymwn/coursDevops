package edu.esiea.coursDevOps.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
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
        assertEquals(mockedUser.getId(), user.getId());
        assertEquals(mockedUser.getLogin(), user.getLogin());
        assertEquals(mockedUser.getPassword(), user.getPassword());

        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).findById(1);
	}

	@Test
	void testGetByLogin() {
		User mockedUser = new User(2,"login2", "password2");
		
		// Simuler le comportement du repository
        when(repo.findByLogin("login2")).thenReturn(Optional.of(mockedUser));

        // Exécuter la méthode à tester
        User user = service.get("login2");

        // Vérifier que le résultat est correct
        assertNotNull(user);
        assertEquals(mockedUser.getId(), user.getId());
        assertEquals(mockedUser.getLogin(), user.getLogin());
        assertEquals(mockedUser.getPassword(), user.getPassword());

        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).findByLogin("login2");
	}

	@Test
	void testGetAll() {
		List<User> mockedList = new ArrayList<>();
		mockedList.add(new User(1,"login", "password"));
		mockedList.add(new User(2,"login2", "password2"));
		
		// Simuler le comportement du repository
        when(repo.findAll()).thenReturn(mockedList);

        // Exécuter la méthode à tester
        List<User> users = service.getAll();

        // Vérifier que le résultat est correct
        assertNotNull(users);
        assertEquals(mockedList.size(), users.size());
        assertEquals(mockedList.getFirst().getId(), users.getFirst().getId());

        // Vérifier que la méthode du repository a bien été appelée une fois
        verify(repo, times(1)).findAll();
	}
}
