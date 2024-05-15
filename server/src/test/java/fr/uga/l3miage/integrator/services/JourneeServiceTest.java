package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.JourneeComponent;
import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.requests.CreerLivraisonDTO;
import fr.uga.l3miage.integrator.requests.CreerTourneeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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
    @MockBean
    private JourneeComponent journeeComponent;



    @Test
    void findJourneeByEntrepotAndDateNotFound() {
        when(journeeRepository.findAll()).thenReturn(List.of());

        LocalDate date = LocalDate.of(2024, 1, 1);

        assertThrows(NotFoundEntityRestException.class,
                () -> service.findJourneeByEntrepotAndDate("Grenis", date));
    }

    @Test
    void findJourneeByEntrepotAndDateFound() {
        String entrepotNom = "Grenis";
        LocalDate date = LocalDate.of(2024, 1, 1);

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom(entrepotNom)
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .build();

        JourneeEntity journee = JourneeEntity.builder()
                .id(1L)
                .date(date)
                .entrepot(entrepot).build();

        when(entrepotRepository.findAll()).thenReturn(List.of(entrepot));
        when(journeeRepository.findByEntrepot_NomAndDateEquals(anyString(), any(LocalDate.class))).thenReturn(journee);

        assertThat(service.findJourneeByEntrepotAndDate(entrepotNom, date)).isEqualTo(journee);
    }
/*
    @Test
    void testConstruireJournee_Success() {


        LocalDate date = LocalDate.of(2024, 1, 28);
        String nomEntrepot = "Grenis" ;
        CreerLivraisonDTO livraisonDTO = new CreerLivraisonDTO();
        livraisonDTO.setCommandes(Set.of("commande1", "commande2"));

        CreerTourneeDTO tourneeDTO = new CreerTourneeDTO();
        tourneeDTO.setLettre("A");
        tourneeDTO.setLivraisons(List.of(livraisonDTO));


        JourneeEntity expectedJournee = new JourneeEntity(); // Remplacer par la création appropriée
        when(journeeComponent.construireJournee(date, nomEntrepot, Collections.singleton(tourneeDTO))).thenReturn(expectedJournee);

        JourneeEntity result = JourneeService.construireJournee(date, nomEntrepot, Collections.singleton(tourneeDTO));

        assertNotNull(result);
        assertEquals(expectedJournee, result);
        verify(journeeComponent).construireJournee(date, nomEntrepot, Collections.singleton(tourneeDTO));
    }

 */
}