package fr.uga.l3miage.integrator.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.*;
import fr.uga.l3miage.integrator.requests.JourneePatchDateRequest;
import fr.uga.l3miage.integrator.requests.LivraisonPatchNumeroRequest;
import fr.uga.l3miage.integrator.requests.TourneePatchJourneeRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
    @Autowired
    private EntrepotRepository entrepotRepository;
    @Autowired
    private JourneeRepository journeeRepository;
    @Autowired
    private TourneeRepository tourneeRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;

    @AfterEach
    void clean() {
        livraisonRepository.deleteAll();
        tourneeRepository.deleteAll();
        journeeRepository.deleteAll();
        entrepotRepository.deleteAll();
    }

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
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
    void patchJournee_DateFound() throws Exception {

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("")
                .ville("")
                .codePostal("00000")
                .build();

        entrepotRepository.save(entrepot);
        LocalDate date = LocalDate.of(2024, 1, 1);

        JourneeEntity journee = JourneeEntity.builder()
                .date(date)
                .entrepot(entrepot)
                .build();

        journeeRepository.save(journee);

        final JourneePatchDateRequest requestObj = JourneePatchDateRequest.builder().date(LocalDate.now()).build();
        final MockHttpServletRequestBuilder request = patch("/api/journee/j001G/date")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isOk());
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
    void patchTournee_JourneeFound() throws Exception {

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("")
                .ville("")
                .codePostal("00000")
                .build();

        JourneeEntity journee = JourneeEntity.builder()
                .date(LocalDate.now())
                .entrepot(entrepot)
                .build();

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(EtatsDeTournee.PLANIFIEE)
                .journee(journee)
                .build();

        entrepotRepository.save(entrepot);
        journeeRepository.save(journee);
        tourneeRepository.save(tournee);

        final LivraisonPatchNumeroRequest requestObj = LivraisonPatchNumeroRequest.builder().numero(2).build();
        final MockHttpServletRequestBuilder request = patch("/api/tournee/t001G-A/journee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void patchLivraison_NumeroNotFound() throws Exception {
        final LivraisonPatchNumeroRequest requestObj = LivraisonPatchNumeroRequest.builder().numero(2).build();
        final MockHttpServletRequestBuilder request = patch("/api/livraison/l001G-A1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void patchLivraison_NumeroFound() throws Exception {

        LivraisonEntity livraison = LivraisonEntity.builder()
                .numero(1)
                .etat(EtatsDeLivraison.PLANIFIEE)
                .build();

        livraisonRepository.save(livraison);

        final LivraisonPatchNumeroRequest requestObj = LivraisonPatchNumeroRequest.builder().numero(2).build();
        final MockHttpServletRequestBuilder request = patch("/api/livraison/j001G")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isOk());
    }
}
