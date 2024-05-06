package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.response.ClientDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientMapperTest {

    private final ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Test
    void toDTO() {

        ClientEntity entity = ClientEntity.builder()
                .email("aalves@brt.fr")
                .nom("ALVES")
                .prenom("ravi")
                .adresse("3 Chemin des Tilleuls")
                .codePostal("38100")
                .ville("Grenoble")
                .build();

        CommandeEntity commande = CommandeEntity.builder()
                .reference("c001")
                .dateCreation(LocalDateTime.now())
                .etat(EtatsDeCommande.OUVERTE)
                .client(entity)
                .build();

        ClientDTO dto = ClientDTO.builder()
                .email(entity.getEmail())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .etat(EtatsDeClient.LIVRABLE)
                .adresse(entity.getAdresse())
                .codePostal(entity.getCodePostal())
                .ville(entity.getVille())
                .commandes(Set.of(commande.getReference()))
                .build();

        entity.setCommandes(Set.of(commande));

        assertThat(mapper.toDto(entity)).isEqualTo(dto);
    }
}
