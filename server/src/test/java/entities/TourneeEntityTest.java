package entities;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TourneeEntityTest {

    @Test
    void getReference28Janvier2024LettreA() {
        Date date = new Date(2024, Calendar.JANUARY, 28);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();
        TourneeEntity tournee = TourneeEntity.builder().lettre("A").journee(journee).build();

        assertThat(tournee.getReference()).isEqualTo("t028G-A");
    }
}
