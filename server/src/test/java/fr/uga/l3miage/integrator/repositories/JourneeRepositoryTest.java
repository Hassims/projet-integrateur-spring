package fr.uga.l3miage.integrator.repositories;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JourneeRepositoryTest {


    @Autowired
    private EntrepotRepository entrepotRepository;
    @Autowired
    private JourneeRepository journeeRepository ;

    @BeforeEach
    @AfterEach
    void clean() {
        journeeRepository.deleteAll();
        entrepotRepository.deleteAll();
    }

    @Test
    void  findByEntrepotNomAndDate(){

        EntrepotEntity findbyEntrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("")
                .codePostal("38000")
                .ville("Grenoble")
                .build();
        entrepotRepository.save(findbyEntrepot) ;

        JourneeEntity findbyjournee = JourneeEntity.builder()
                .id(1L)
                .entrepot(findbyEntrepot)
                .date(LocalDate.of(2024, 1 ,1))
                .etat(EtatsDeJournee.PLANIFIEE)
                .build();
        journeeRepository.save(findbyjournee) ;

        String nomEntrepot = findbyEntrepot.getNom() ;

        JourneeEntity resultat = journeeRepository.findByEntrepot_NomAndDateEquals(nomEntrepot,findbyjournee.getDate()) ;

        assertThat(resultat).isEqualTo(findbyjournee) ;

    }


}
