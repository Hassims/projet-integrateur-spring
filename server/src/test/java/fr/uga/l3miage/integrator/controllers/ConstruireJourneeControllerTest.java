package fr.uga.l3miage.integrator.controllers;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.requests.ConstruireJourneePostRequest;
import fr.uga.l3miage.integrator.requests.CreerLivraisonDTO;
import fr.uga.l3miage.integrator.requests.CreerTourneeDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class ConstruireJourneeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private EntrepotRepository entrepotRepository;

    @AfterEach
    void clean() {
        commandeRepository.deleteAll();
    }

    @Test
    void getCommandesEmpty() throws Exception {
        mockMvc.perform(get("/journees"));
    }

    @Test
    void getCommandesNotEmpty() throws Exception {
        CommandeEntity c1 = CommandeEntity.builder()
                .reference("c001")
                .dateCreation(LocalDateTime.now())
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();
        CommandeEntity c2 = CommandeEntity.builder()
                .reference("c002")
                .dateCreation(LocalDateTime.now())
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();
        CommandeEntity c3 = CommandeEntity.builder()
                .reference("c003")
                .dateCreation(LocalDateTime.now())
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();

        commandeRepository.saveAll(Set.of(c1, c2, c3));

        mockMvc.perform(get("/journees"));
    }

    @Test
    void postJournee() throws Exception {

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("")
                .ville("")
                .codePostal("00000")
                .build();

        CommandeEntity commande = CommandeEntity.builder()
                .reference("c001")
                .dateCreation(LocalDateTime.now())
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();

        entrepotRepository.save(entrepot);
        commandeRepository.save(commande);

        CreerLivraisonDTO livraisonDTO = CreerLivraisonDTO.builder()
                .commandes(Set.of("c001"))
                .build();

        CreerTourneeDTO tourneeDTO = CreerTourneeDTO.builder()
                .lettre("A")
                .livraisons(List.of(livraisonDTO))
                .build();

        ConstruireJourneePostRequest request = ConstruireJourneePostRequest.builder()
                .date(LocalDate.now())
                .nomEntrepot("Grenis")
                .tournees(Set.of(tourneeDTO))
                .build();

        ConstruireJourneePostRequest requestObj = ConstruireJourneePostRequest.builder()
                .date(LocalDate.now())
                .nomEntrepot("Grenis")
                .tournees(Set.of(tourneeDTO))
                .build();

        /*final MockHttpServletRequestBuilder request = post("/api/journee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(requestObj));*/

        mockMvc.perform(post("/journee", requestObj));
    }
}
