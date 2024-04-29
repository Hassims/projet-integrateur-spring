package fr.uga.l3miage.integrator.controllers;

import com.google.gson.Gson;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.requests.TourneePatchRequest;
import fr.uga.l3miage.integrator.services.ChargerTourneeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class VisualiserTourneeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private ChargerTourneeService service;
    @Autowired
    private TourneeRepository tourneeRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    @Test
    void getAllEntrepot() throws Exception {
        final MockHttpServletRequestBuilder request = get("/entrepot");

        mockMvc.perform(request).andExpect(status().isFound());
    }
}
