package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.LivraisonComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LivraisonServiceTest {

    @Autowired
    private LivraisonService service;
    @MockBean
    LivraisonComponent livraisonComponent;

    @Test
    void updateEtatNotFound() throws Exception {

        when(livraisonComponent.findByReference(anyString())).thenThrow(new Exception());

        assertThrows(
                NotFoundEntityRestException.class,
                () -> service.updateEtat("l001G-A1", EtatsDeLivraison.PLANIFIEE)
        );
    }

    @Test
    void updateEtatEnDechargement() throws Exception {

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(EtatsDeTournee.EN_PARCOURS)
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.EN_PARCOURS)
                .numero(1)
                .tournee(tournee)
                .build();


        when(livraisonComponent.findByReference(anyString())).thenReturn(livraison);

        service.updateEtat("l001G-A1", EtatsDeLivraison.EN_DECHARGEMENT);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.EN_DECHARGEMENT);
        assertThat(livraison.getEtat()).isEqualTo(EtatsDeLivraison.EN_DECHARGEMENT);
    }

    @Test
    void updateEtatEnClientele() throws Exception {

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(EtatsDeTournee.EN_DECHARGEMENT)
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.EN_DECHARGEMENT)
                .numero(1)
                .tournee(tournee)
                .build();


        when(livraisonComponent.findByReference(anyString())).thenReturn(livraison);

        service.updateEtat("l001G-A1", EtatsDeLivraison.EN_CLIENTELE);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.EN_CLIENTELE);
        assertThat(livraison.getEtat()).isEqualTo(EtatsDeLivraison.EN_CLIENTELE);
    }

    @Test
    void updateEtatEnMontage() throws Exception {

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(EtatsDeTournee.EN_CLIENTELE)
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.EN_CLIENTELE)
                .numero(1)
                .tournee(tournee)
                .build();


        when(livraisonComponent.findByReference(anyString())).thenReturn(livraison);

        service.updateEtat("l001G-A1", EtatsDeLivraison.EN_MONTAGE);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.EN_MONTAGE);
        assertThat(livraison.getEtat()).isEqualTo(EtatsDeLivraison.EN_MONTAGE);
    }

    @Test
    void updateEtatEffectueeLastLivraison() throws Exception {

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(EtatsDeTournee.EN_CLIENTELE)
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.EN_CLIENTELE)
                .numero(1)
                .tournee(tournee)
                .build();

        tournee.setLivraisons(List.of(livraison));

        when(livraisonComponent.findByReference(anyString())).thenReturn(livraison);

        service.updateEtat("l001G-A1", EtatsDeLivraison.EFFECTUEE);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.EN_RETOUR);
        assertThat(livraison.getEtat()).isEqualTo(EtatsDeLivraison.EFFECTUEE);
    }

    @Test
    void updateEtatEffectueeNotLastLivraison() throws Exception {

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(EtatsDeTournee.EN_CLIENTELE)
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.EN_CLIENTELE)
                .numero(1)
                .tournee(tournee)
                .build();

        LivraisonEntity otherlivraison = LivraisonEntity.builder()
                .etat(EtatsDeLivraison.EN_PARCOURS)
                .numero(2)
                .tournee(tournee)
                .build();

        tournee.setLivraisons(List.of(livraison, otherlivraison));

        when(livraisonComponent.findByReference(anyString())).thenReturn(livraison);

        service.updateEtat("l001G-A1", EtatsDeLivraison.EFFECTUEE);

        assertThat(tournee.getEtat()).isEqualTo(EtatsDeTournee.EN_PARCOURS);
        assertThat(livraison.getEtat()).isEqualTo(EtatsDeLivraison.EFFECTUEE);
    }
}
