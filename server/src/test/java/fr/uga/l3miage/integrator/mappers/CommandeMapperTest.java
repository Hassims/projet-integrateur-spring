package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LigneEntity;
import fr.uga.l3miage.integrator.models.ProduitEntity;
import fr.uga.l3miage.integrator.response.CommandeDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandeMapperTest {

    private final CommandeMapper mapper = Mappers.getMapper(CommandeMapper.class);
    @Test
    void toDTO() {

        ClientEntity client = ClientEntity.builder()
                .email("aalves@brt.fr")
                .nom("ALVES")
                .prenom("ravi")
                .adresse("3 Chemin des Tilleuls")
                .codePostal("38100")
                .ville("Grenoble")
                .build();

        CommandeEntity entity = CommandeEntity.builder()
                .reference("c001")
                .dateCreation(LocalDateTime.now())
                .etat(EtatsDeCommande.OUVERTE)
                .client(client)
                .build();

        ProduitEntity produit = ProduitEntity.builder()
                .reference("p001")
                .prix(3.99)
                .build();

        LigneEntity ligne = LigneEntity.builder()
                .commande(entity)
                .produit(produit)
                .quantite(10)
                .build();

        entity.setClient(client);
        entity.setLignes(Set.of(ligne));

        CommandeDTO dto = CommandeDTO.builder()
                .dateCreation(entity.getDateCreation())
                .etat(entity.getEtat())
                .reference(entity.getReference())
                .client(client.getEmail())
                .montant(entity.getMontant())
                .tempsMontageTheorique(entity.getTempsMontageTheorique())
                .build();

        assertThat(mapper.toDTO(entity)).isEqualTo(dto);
    }
}
