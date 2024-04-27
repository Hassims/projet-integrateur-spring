package fr.uga.l3miage.integrator.entities;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class JourneeEntityTest {

    @Test
    void getReference28Janvier2024() {
        LocalDate date = LocalDate.of(2024, 1, 28);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();

        assertThat(journee.getReference()).isEqualTo("j028G");
    }
}
