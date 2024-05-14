package fr.uga.l3miage.integrator.components;


import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.requests.CreerLivraisonDTO;
import fr.uga.l3miage.integrator.requests.CreerTourneeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JourneeComponentTest {

    @Autowired
    private JourneeComponent component;
    @MockBean
    private JourneeRepository journeeRepository;

    @MockBean
    private TourneeRepository tourneeRepository;

    @MockBean
    private EntrepotRepository entrepotRepository ;

/*
    @Test
    void findJourneeApresConstruction()throws Exception{

        LocalDate date = LocalDate.of(2024, 1, 28);
        String nomEntrepot = "Grenis" ;
        CreerLivraisonDTO livraisonDTO = new CreerLivraisonDTO();
        livraisonDTO.setCommandes(Set.of("commande1", "commande2"));

        CreerTourneeDTO tourneeDTO = new CreerTourneeDTO();
        tourneeDTO.setLettre("A");
        tourneeDTO.setLivraisons(List.of(livraisonDTO));


        JourneeEntity journee = component.construireJournee(date,nomEntrepot, Collections.singleton(tourneeDTO)) ;

        assertThat(journeeRepository.findByEntrepot_NomAndDateEquals(nomEntrepot,date)).isEqualTo(journee);

    }
*/

}
