package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeServiceTest {

    @Autowired
    private TourneeService service;
    @MockBean
    private TourneeComponent tourneeComponent;
    @MockBean
    private TourneeRepository tourneeRepository;
    @MockBean
    private LivraisonRepository livraisonRepository;
    @MockBean
    private CommandeRepository commandeRepository;

    @Test
    void findByReferenceFound() throws Exception {
        TourneeEntity tourneeExpected = TourneeEntity.builder().lettre("A").build();

        when(tourneeComponent.findByReference("t001G-A")).thenReturn(tourneeExpected);

        TourneeEntity tourneeFound = service.findByReference("t001G-A");

        assertThat(tourneeFound).isEqualTo(tourneeExpected);
    }

    @Test
    void findByReferenceNotFound() throws Exception {
        when(tourneeComponent.findByReference(anyString())).thenThrow(new Exception());
        assertThrows(NotFoundEntityRestException.class, () -> service.findByReference("t001G-A"));
    }

    @Test
    void updateTourneeEtatFromPlanifieeToEnChargement() throws Exception {
        TourneeEntity tournee = TourneeEntity.builder().lettre("A").etat(EtatsDeTournee.planifiee).build();

        when(tourneeComponent.findByReference(anyString())).thenReturn(tournee);
        service.updateTourneeEtat("t001G-A", EtatsDeTournee.enChargement);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.enChargement);
    }

    @Test
    void updateTourneeEtatFromEnChargementToEnParcours() throws Exception {

        TourneeEntity tournee = TourneeEntity.builder()
                .etat(EtatsDeTournee.enChargement)
                .lettre("A")
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.planifiee)
                .numero(1)
                .tournee(tournee)
                .build();

        CommandeEntity commande = CommandeEntity.builder()
                .reference("c001")
                .etat(EtatsDeCommande.planifiee)
                .dateCreation(LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .livraison(livraison)
                .build();

        tournee.setLivraisons(List.of(livraison));
        livraison.setCommandes(Set.of(commande));

        when(tourneeComponent.findByReference(anyString())).thenReturn(tournee);
        when(tourneeRepository.save(any())).thenReturn(tournee);
        when(livraisonRepository.save(any())).thenReturn(livraison);
        when(commandeRepository.save(any())).thenReturn(commande);

        service.updateTourneeEtat("t001G-A", EtatsDeTournee.enParcours);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.enParcours);
        assertThat(livraison.getEtat()).isEqualTo(EtatsDeLivraison.enParcours);
        assertThat(commande.getEtat()).isEqualTo(EtatsDeCommande.enLivraison);
    }

    @Test
    void updateTourneeEtatNotFound() throws Exception {
        when(tourneeComponent.findByReference(anyString())).thenThrow(new Exception());

        assertThrows(
                NotFoundEntityRestException.class,
                () -> service.updateTourneeEtat("t001G-A", EtatsDeTournee.enChargement));
    }
}
