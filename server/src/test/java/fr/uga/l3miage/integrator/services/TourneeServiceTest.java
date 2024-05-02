package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
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
    @Autowired
    private TourneeRepository tourneeRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @MockBean
    private TourneeComponent component;

    @Test
    void updateTourneeEtatFromPlanifieeToEnChargement() {
        TourneeEntity tournee = TourneeEntity.builder().lettre("A").etat(EtatsDeTournee.PLANIFIEE).build();

        when(component.findByReference(anyString())).thenReturn(Optional.of(tournee));
        service.updateTourneeEtat("t028G-A", EtatsDeTournee.EN_CHARGEMENT);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.EN_CHARGEMENT);
    }

    @Test
    void updateTourneeEtatFromEnChargementToEnParcours() {
        TourneeEntity tournee = TourneeEntity.builder()
                .etat(EtatsDeTournee.EN_CHARGEMENT)
                .lettre("A")
                .build();
        LivraisonEntity livraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.PLANIFIEE)
                .numero(1)
                .tournee(tournee)
                .build();
        CommandeEntity commande = CommandeEntity.builder()
                .reference("c001")
                .etat(EtatsDeCommande.PLANIFIEE)
                .dateCreation(LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .livraison(livraison)
                .build();

        tournee.setLivraisons(List.of(livraison));
        livraison.setCommandes(Set.of(commande));

        tourneeRepository.save(tournee);
        livraisonRepository.save(livraison);
        commandeRepository.save(commande);

        when(component.findByReference(anyString())).thenReturn(Optional.of(tournee));
        service.updateTourneeEtat("t028G-A", EtatsDeTournee.EN_PARCOURS);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.EN_PARCOURS);
        assertThat(livraison.getEtat()).isEqualTo(EtatsDeLivraison.EN_PARCOURS);
        assertThat(commande.getEtat()).isEqualTo(EtatsDeCommande.EN_LIVRAISON);
    }

    @Test
    void updateTourneeEtatNotFound() {
        when(component.findByReference(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundEntityRestException.class,
                () -> service.updateTourneeEtat("t028G-A", EtatsDeTournee.EN_CHARGEMENT));
    }
}
