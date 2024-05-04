package fr.uga.l3miage.integrator.controllers;

import com.google.gson.Gson;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.repositories.*;
import fr.uga.l3miage.integrator.requests.LivraisonPatchEtatRequest;
import org.hibernate.Hibernate;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class EffectuerLivraisonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private TourneeRepository tourneeRepository;
    @Autowired
    private EntrepotRepository entrepotRepository;
    @Autowired
    private JourneeRepository journeeRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    LivraisonMapper livraisonMapper;

    @AfterEach
    void clean() {
        commandeRepository.deleteAll();
        livraisonRepository.deleteAll();
        tourneeRepository.deleteAll();
        journeeRepository.deleteAll();
        entrepotRepository.deleteAll();
    }

    LivraisonEntity populateDB(EtatsDeJournee etatJournee, EtatsDeTournee etatTournee, EtatsDeLivraison etatLivraison, EtatsDeCommande etatCommande) {
        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("")
                .codePostal("00000")
                .ville("")
                .build();

        JourneeEntity journee = JourneeEntity.builder()
                .etat(etatJournee)
                .date(LocalDate.of(2024, 1, 1))
                .entrepot(entrepot)
                .build();

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(etatTournee)
                .journee(journee)
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .numero(1)
                .etat(etatLivraison)
                .tournee(tournee)
                .build();

        CommandeEntity commande = CommandeEntity.builder()
                .reference("c001")
                .etat(etatCommande)
                .dateCreation(LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .livraison(livraison)
                .build();

        entrepotRepository.save(entrepot);
        journeeRepository.save(journee);
        tourneeRepository.save(tournee);
        livraisonRepository.save(livraison);
        commandeRepository.save(commande);

        entrepot.setJournees(Set.of(journee));
        journee.setTournees(Set.of(tournee));
        tournee.setLivraisons(List.of(livraison));
        livraison.setCommandes(Set.of(commande));

        entrepotRepository.save(entrepot);
        journeeRepository.save(journee);
        tourneeRepository.save(tournee);

        return livraisonRepository.save(livraison);
    }

    @Test
    void patchEtatNotFound() throws Exception {

        final LivraisonPatchEtatRequest requestObj = LivraisonPatchEtatRequest
                .builder()
                .etat(EtatsDeLivraison.PLANIFIEE)
                .build();

        final MockHttpServletRequestBuilder request = patch("/api/livraison/l001G-A1/etat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    void patchEtatEnDechargement() throws Exception {

        LivraisonEntity livraison = populateDB(
                EtatsDeJournee.EN_COURS,
                EtatsDeTournee.EN_PARCOURS,
                EtatsDeLivraison.EN_PARCOURS,
                EtatsDeCommande.EN_LIVRAISON
        );

        Hibernate.initialize(livraison.getTournee());

        final LivraisonPatchEtatRequest requestObj = LivraisonPatchEtatRequest
                .builder()
                .etat(EtatsDeLivraison.EN_DECHARGEMENT)
                .build();

        String reference = livraison.getReference();

        final MockHttpServletRequestBuilder request = patch("/api/livraison/" + reference + "/etat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void patchEtatEnClientele() throws Exception {

        LivraisonEntity livraison = populateDB(
                EtatsDeJournee.EN_COURS,
                EtatsDeTournee.EN_DECHARGEMENT,
                EtatsDeLivraison.EN_DECHARGEMENT,
                EtatsDeCommande.EN_LIVRAISON
        );

        Hibernate.initialize(livraison.getTournee());

        final LivraisonPatchEtatRequest requestObj = LivraisonPatchEtatRequest
                .builder()
                .etat(EtatsDeLivraison.EN_CLIENTELE)
                .build();

        final MockHttpServletRequestBuilder request = patch("/api/livraison/" + livraison.getReference() + "/etat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void patchEtatEnMontage() throws Exception {

        LivraisonEntity livraison = populateDB(
                EtatsDeJournee.EN_COURS,
                EtatsDeTournee.EN_CLIENTELE,
                EtatsDeLivraison.EN_CLIENTELE,
                EtatsDeCommande.EN_LIVRAISON
        );

        Hibernate.initialize(livraison.getTournee());

        final LivraisonPatchEtatRequest requestObj = LivraisonPatchEtatRequest
                .builder()
                .etat(EtatsDeLivraison.EN_MONTAGE)
                .build();

        final MockHttpServletRequestBuilder request = patch("/api/livraison/" + livraison.getReference() + "/etat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void patchEtatEffectuee() throws Exception {

        LivraisonEntity livraison = populateDB(
                EtatsDeJournee.EN_COURS,
                EtatsDeTournee.EN_CLIENTELE,
                EtatsDeLivraison.EN_CLIENTELE,
                EtatsDeCommande.EN_LIVRAISON
        );

        Hibernate.initialize(livraison.getTournee());

        final LivraisonPatchEtatRequest requestObj = LivraisonPatchEtatRequest
                .builder()
                .etat(EtatsDeLivraison.EFFECTUEE)
                .build();

        final MockHttpServletRequestBuilder request = patch("/api/livraison/" + livraison.getReference() + "/etat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(requestObj));

        mockMvc.perform(request).andExpect(status().isOk());
    }
}
