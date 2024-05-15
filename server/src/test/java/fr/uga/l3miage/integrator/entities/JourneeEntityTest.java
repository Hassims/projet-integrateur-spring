package fr.uga.l3miage.integrator.entities;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JourneeEntityTest {

    @Test
    void getReference28Janvier2024() {
        LocalDate date = LocalDate.of(2024, 1, 28);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();

        assertThat(journee.getReference()).isEqualTo("j028G");
    }

    @Test
    void getEtatPlanifiee() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();

        TourneeEntity t1 = TourneeEntity.builder().lettre("A").etat(EtatsDeTournee.PLANIFIEE).journee(journee).build();
        TourneeEntity t2 = TourneeEntity.builder().lettre("B").etat(EtatsDeTournee.PLANIFIEE).journee(journee).build();
        TourneeEntity t3 = TourneeEntity.builder().lettre("C").etat(EtatsDeTournee.PLANIFIEE).journee(journee).build();

        journee.setTournees(Set.of(t1, t2, t3));

        assertThat(journee.getEtat()).isEqualTo(EtatsDeJournee.PLANIFIEE);
    }

    @Test
    void getEtatEnCours() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();

        TourneeEntity t1 = TourneeEntity.builder().etat(EtatsDeTournee.PLANIFIEE).journee(journee).build();
        TourneeEntity t2 = TourneeEntity.builder().etat(EtatsDeTournee.EFFECTUEE).journee(journee).build();
        TourneeEntity t3 = TourneeEntity.builder().etat(EtatsDeTournee.EN_CLIENTELE).journee(journee).build();

        journee.setTournees(Set.of(t1, t2, t3));

        assertThat(journee.getEtat()).isEqualTo(EtatsDeJournee.EN_COURS);
    }

    @Test
    void getEtatEffectuee() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();

        TourneeEntity t1 = TourneeEntity.builder().lettre("A").etat(EtatsDeTournee.EFFECTUEE).journee(journee).build();
        TourneeEntity t2 = TourneeEntity.builder().lettre("B").etat(EtatsDeTournee.EFFECTUEE).journee(journee).build();
        TourneeEntity t3 = TourneeEntity.builder().lettre("C").etat(EtatsDeTournee.EFFECTUEE).journee(journee).build();

        journee.setTournees(Set.of(t1, t2, t3));

        assertThat(journee.getEtat()).isEqualTo(EtatsDeJournee.EFFECTUEE);
    }
}
