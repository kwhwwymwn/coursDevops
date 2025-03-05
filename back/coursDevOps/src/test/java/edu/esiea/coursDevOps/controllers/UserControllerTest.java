package edu.esiea.coursDevOps.controllers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.services.UserService;

@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
	private UserService service;

    @Test
    void testGetOne() throws Exception {
        // Simulate service response
        User mockedUser = new User(1, "login", "password");
        when(service.get(1)).thenReturn(mockedUser);

        // Perform GET request
        mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockedUser.getId()))
                .andExpect(jsonPath("$.login").value(mockedUser.getLogin()));

        // Verify service method was called once
        verify(service, times(1)).get(1);
    }

	@Test
	void testGetAll() {
        // Simuler la réponse du service
		List<User> mockedList = new ArrayList<>();
		mockedList.add(new User(1,"login", "password"));
		mockedList.add(new User(2,"login2", "password2"));
        when(service.getAll()).thenReturn(mockedList);

        // Exécuter la requête GET
        try {
			mockMvc.perform(get("/users/all")
			        .contentType(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk())
			        .andExpect(jsonPath("$[0].id").value(mockedList.getFirst().getId()))
			        .andExpect(jsonPath("$[1].login").value(mockedList.getLast().getLogin()));
		} catch (Exception e) {
			fail(e);
		}

        // Vérifier que la méthode du service a été appelée une fois
        verify(service, times(1)).getAll();

	}

	@Test
	void testLogin() {
        // Simuler la réponse du service
		User mockedUser = new User(2,"login2", "password2");
        when(service.get("login2")).thenReturn(mockedUser);

        // Exécuter la requête Post
        try {
			mockMvc.perform(post("/users/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(new User(0,"login2", "password2")))
			        .accept(MediaType.APPLICATION_JSON))
			        .andExpect(status().isOk());
		} catch (Exception e) {
			fail(e);
		}

        // Vérifier que la méthode du service a été appelée une fois
        verify(service, times(1)).get("login2");
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
}
