package edu.esiea.coursDevOps.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.esiea.coursDevOps.models.User;
import edu.esiea.coursDevOps.services.UserService;




@WebMvcTest(UserController.class) 
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
	private UserService service;

    @Test
    void testGetOne() throws Exception {
        // Simuler la réponse du service
    	User mockedUser = new User(1,"login", "password");
        when(service.get(1)).thenReturn(mockedUser);

        // Exécuter la requête GET
        mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockedUser.getId()))
                .andExpect(jsonPath("$.login").value(mockedUser.getLogin()));

        // Vérifier que la méthode du service a été appelée une fois
        verify(service, times(1)).get(1);
    }
}
