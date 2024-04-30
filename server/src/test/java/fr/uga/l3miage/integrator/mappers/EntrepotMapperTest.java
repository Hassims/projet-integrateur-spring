package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.EntrepotDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EntrepotMapperTest {

    @Autowired
    private EntrepotMapper mapper;

    @Test
    void EntityWithNavigationPropertiesToDTO() {
        StockEntity stock = StockEntity.builder().id(1L).build();
        JourneeEntity journee = JourneeEntity.builder().id(1L).build();
        CamionEntity camion = CamionEntity.builder().immatriculation("AB-123-CD").build();
        EmployeEntity employe = EmployeEntity.builder().trigramme("STR").build();

        EntrepotEntity entity = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .stocks(Set.of(stock))
                .journees(Set.of(journee))
                .camions(Set.of(camion))
                .employes(Set.of(employe))
                .build();

        EntrepotDTO dto = EntrepotDTO.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .stocks(Set.of(stock.getId()))
                .journees(Set.of(journee.getId()))
                .employes(Set.of(employe.getTrigramme()))
                .camions(Set.of(camion.getImmatriculation()))
                .build();

        assertThat(mapper.toDTO(entity)).isEqualTo(dto);
    }

    @Test
    void EntityWithoutNavigationPropertiesToDTO() {

        EntrepotEntity entity = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .build();

        EntrepotDTO dto = EntrepotDTO.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .stocks(Set.of())
                .journees(Set.of())
                .employes(Set.of())
                .camions(Set.of())
                .build();

        assertThat(mapper.toDTO(entity)).isEqualTo(dto);
    }

    @Test
    void DTOWithNavigationPropertiesToEntity() {

        StockEntity stock = StockEntity.builder().id(1L).build();
        JourneeEntity journee = JourneeEntity.builder().id(1L).build();
        CamionEntity camion = CamionEntity.builder().immatriculation("AB-123-CD").build();
        EmployeEntity employe = EmployeEntity.builder().trigramme("STR").build();

        EntrepotDTO dto = EntrepotDTO.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .stocks(Set.of(stock.getId()))
                .journees(Set.of(journee.getId()))
                .employes(Set.of(employe.getTrigramme()))
                .camions(Set.of(camion.getImmatriculation()))
                .build();

        EntrepotEntity entity = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .stocks(Set.of(stock))
                .journees(Set.of(journee))
                .camions(Set.of(camion))
                .employes(Set.of(employe))
                .build();

        //assertThat(mapper.toEntity(dto)).isEqualTo(entity);
    }

    @Test
    void DTOWithoutNavigationPropertiesToEntity() {

        EntrepotDTO dto = EntrepotDTO.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .stocks(Set.of())
                .journees(Set.of())
                .employes(Set.of())
                .camions(Set.of())
                .build();

        EntrepotEntity entity = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .build();

        //assertThat(mapper.toEntity(dto)).isEqualTo(entity);
    }
}
