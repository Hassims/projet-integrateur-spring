package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JourneeMapperTest {

    private final JourneeMapper mapper = Mappers.getMapper(JourneeMapper.class);

    @Test
    void EntityToDTO() {

        LocalDate date = LocalDate.of(2024, 1, 1);
        EtatsDeJournee etat = EtatsDeJournee.PLANIFIEE;

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .build();

        JourneeEntity entity = JourneeEntity.builder()
                .date(date)
                .etat(etat)
                .entrepot(entrepot)
                .build();

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .journee(entity)
                .build();

        entity.setTournees(Set.of(tournee));

        JourneeDTO dto = JourneeDTO.builder()
                .reference(entity.getReference())
                .etat(etat)
                .date(date)
                .entrepot(entrepot.getNom())
                .tournees(Set.of(tournee.getReference()))
                .build();

        assertThat(mapper.toDTO(entity)).isEqualTo(dto);
    }
}
