package entities;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class JourneeEntityTest {

    @Test
    void getReference28Janvier2024() {
        Date date = new Date(2024, Calendar.JANUARY, 28);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();

        assertThat(journee.getReference()).isEqualTo("j028G");
    }
}
