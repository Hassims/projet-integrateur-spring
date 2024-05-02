package fr.uga.l3miage.integrator.controllers;

import com.google.gson.Gson;
import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.models.CamionEntity;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.CamionRepository;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class VisualiserTourneeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntrepotRepository entrepotRepository;
    @Autowired
    private JourneeRepository journeeRepository ;
    @Autowired
    private TourneeRepository tourneeRepository ;
    @Autowired
    private CamionRepository camionRepository ;


    @Autowired
    private EntrepotMapper entrepotMapper;

    @BeforeEach
    @AfterEach
    void clean() {
        tourneeRepository.deleteAll();
        journeeRepository.deleteAll();
        entrepotRepository.deleteAll();
        camionRepository.deleteAll();
    }

    @Test
    void getAllEntrepotEmpty() throws Exception {
        final MockHttpServletRequestBuilder request = get("/api/entrepot");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllEntrepotNotEmpty() throws Exception {
        EntrepotEntity e1 = EntrepotEntity.builder()
                .nom("Grenis").lettre("G").adresse("").codePostal("00000").ville("").build();

        EntrepotEntity e2 = EntrepotEntity.builder()
                .nom("Bronis").lettre("B").adresse("").codePostal("00000").ville("").build();

        EntrepotEntity e3 = EntrepotEntity.builder()
                .nom("Albis").lettre("A").adresse("").codePostal("00000").ville("").build();

        List<EntrepotEntity> entityList = List.of(e1, e2, e3);

        entrepotRepository.saveAll(entityList);

        String expectedJsonResult = new Gson().toJson(entrepotMapper.toDTOList(entityList));

        mockMvc.perform(get("/api/entrepot"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResult));
    }

    @Test
    void getJourneeByEntrepotAndDateNotFound() throws Exception {

        mockMvc.perform(get("/entrepot/Grenis/journee/2024-01-01"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getJourneeByEntrepotAndDateFound() throws Exception {

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom("Grenis").lettre("G").adresse("").codePostal("00000").ville("").build();

        entrepotRepository.save(entrepot);

        JourneeEntity journee = JourneeEntity.builder()
                .id(1L)
                .entrepot(entrepot)
                .date(LocalDate.of(2024, 1 ,1))
                .etat(EtatsDeJournee.PLANIFIEE)
                .build();

        entrepotRepository.save(entrepot);
        journeeRepository.save(journee);

        mockMvc.perform(get("/entrepot/Grenis/journee/2024-01-01"))
                .andExpect(status().isOk());
    }

    @Test
    void getVisuTournee() throws Exception {
        CamionEntity visuCamion = CamionEntity.builder()
                .immatriculation("XY-456-ZT")
                .build();
        camionRepository.save(visuCamion) ;

        EntrepotEntity visuEntrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("")
                .codePostal("38000")
                .ville("Grenoble")
                .camions(Set.of(visuCamion))
                .build();
        entrepotRepository.save(visuEntrepot) ;


        JourneeEntity visuJournee = JourneeEntity.builder()
                .etat(EtatsDeJournee.PLANIFIEE)
                .entrepot(visuEntrepot)
                .date(LocalDate.of(2024,1,28))
                .build();

        journeeRepository.save(visuJournee) ;

        TourneeEntity visuTournee = TourneeEntity.builder()
                .etat(EtatsDeTournee.PLANIFIEE)
                .lettre("A")
                .journee(visuJournee)
                .camion(visuCamion)
                .build();

        tourneeRepository.save(visuTournee) ;
        journeeRepository.save(visuJournee) ;

        visuJournee.setTournees(Set.of(visuTournee));

        entrepotRepository.save(visuEntrepot) ;

        String reference = visuTournee.getReference();

        mockMvc.perform(get("/api/tournee/"+reference+"/visu"))
                .andExpect(status().isOk());

    }


    @Test
    void getVisuTourneeNotFound() throws Exception {

        mockMvc.perform(get("/api/tournee/t028G-A/visu"))
                .andExpect(status().isNotFound()) ;
    }
}
