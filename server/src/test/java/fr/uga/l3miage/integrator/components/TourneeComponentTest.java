package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeComponentTest {

    @Autowired
    private TourneeComponent component;
    @MockBean
    private TourneeRepository repository;

    @Test
    void findByReferenceFound() {
        LocalDate date = LocalDate.of(2024, 1, 28);
        EntrepotEntity entrepot = EntrepotEntity.builder().lettre("G").build();
        JourneeEntity journee = JourneeEntity.builder().date(date).entrepot(entrepot).build();
        TourneeEntity tournee = TourneeEntity.builder().lettre("A").journee(journee).build();

        when(repository.findAll()).thenReturn(List.of(tournee));

        assertThat(component.findByReference("t028G-A").isPresent()).isTrue();
        assertThat(component.findByReference("t028G-A").get()).isEqualTo(tournee);
    }

    @Test
    void findByReferenceNotFound() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        assertThat(component.findByReference("t028G-A").isPresent()).isFalse();
    }
}
