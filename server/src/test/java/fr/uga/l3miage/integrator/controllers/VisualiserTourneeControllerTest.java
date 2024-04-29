package fr.uga.l3miage.integrator.controllers;

import com.google.gson.Gson;
import fr.uga.l3miage.integrator.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

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
    private EntrepotMapper entrepotMapper;

    @AfterEach
    void clean() {
        entrepotRepository.deleteAll();
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
}
