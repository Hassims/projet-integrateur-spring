package fr.uga.l3miage.integrator.controllers;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class ChargerTourneeControllerTest {

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
    void patchTourneeEtatNotFound() throws Exception {
        final TourneePatchRequest requestObj = TourneePatchRequest.builder().etat(EtatsDeTournee.enChargement).build();
        final MockHttpServletRequestBuilder request = patch("/api/tournee/t028G-A")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isNotFound());
    }
}
