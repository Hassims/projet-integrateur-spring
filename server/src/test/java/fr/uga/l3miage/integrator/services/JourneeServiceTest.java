package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JourneeServiceTest {

    @Autowired
    private JourneeService service;
    @MockBean
    private JourneeRepository journeeRepository;
    @MockBean
    private EntrepotRepository entrepotRepository;

    @Test
    void findJourneeByEntrepotAndDateNotFound() {
        when(journeeRepository.findAll()).thenReturn(List.of());

        LocalDate date = LocalDate.of(2024, 1, 1);

        assertThat(service.findJourneeByEntrepotAndDate("Grenis", date))
                .isEqualTo(Optional.empty());
    }

    @Test
    void findJourneeByEntrepotAndDateFound() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        String entrepotNom = "Grenis";

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom(entrepotNom)
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .build();

        JourneeEntity journee = JourneeEntity.builder()
                .id(1L)
                .etat(EtatsDeJournee.PLANIFIEE)
                .date(date)
                .entrepot(entrepot).build();

        when(entrepotRepository.findAll()).thenReturn(List.of(entrepot));
        when(journeeRepository.findAll()).thenReturn(List.of(journee));

        assertThat(service.findJourneeByEntrepotAndDate(entrepotNom, date))
                .isEqualTo(Optional.of(journee));
    }
}