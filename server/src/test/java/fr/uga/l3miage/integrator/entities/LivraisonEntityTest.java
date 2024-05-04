package fr.uga.l3miage.integrator.entities;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class LivraisonEntityTest {

    @Test
    void getReference28Janvier2024LettreANumero1() {
        LocalDate date = LocalDate.of(2024, 1, 28);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();
        TourneeEntity tournee = TourneeEntity.builder().lettre("A").journee(journee).build();
        LivraisonEntity livraison = LivraisonEntity.builder().numero(1).tournee(tournee).build();

        assertThat(livraison.getReference()).isEqualTo("l028G-A1");
    }
}
