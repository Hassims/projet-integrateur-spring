package fr.uga.l3miage.integrator.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.requests.JourneePatchDateRequest;
import fr.uga.l3miage.integrator.requests.LivraisonPatchNumeroRequest;
import fr.uga.l3miage.integrator.requests.TourneePatchJourneeRequest;
import fr.uga.l3miage.integrator.services.TourneeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class AjusterJourneeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private TourneeService service;
    @Autowired
    private TourneeRepository tourneeRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    @Test
    void patchJournee_DateNotFound() throws Exception {
        final JourneePatchDateRequest requestObj = JourneePatchDateRequest.builder().date(LocalDate.now()).build();
        final MockHttpServletRequestBuilder request = patch("/api/journee/j001G/date")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void patchTournee_JourneeNotFound() throws Exception {
        final TourneePatchJourneeRequest requestObj = TourneePatchJourneeRequest.builder().journee("j001G").build();
        final MockHttpServletRequestBuilder request = patch("/api/tournee/t001G-A/journee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void patchLivraison_NumeroNotFound() throws Exception {
        final LivraisonPatchNumeroRequest requestObj = LivraisonPatchNumeroRequest.builder().numero(2).build();
        final MockHttpServletRequestBuilder request = patch("/api/livraison/l001G-A1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isNotFound());
    }
}
