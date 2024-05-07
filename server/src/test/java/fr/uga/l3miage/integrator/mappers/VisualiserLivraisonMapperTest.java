package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.ClientDTO;
import fr.uga.l3miage.integrator.response.VisualiserLivraisonDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class VisualiserLivraisonMapperTest {

    private final VisualiserLivraisonMapper mapper = new VisualiserLivraisonMapper();

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

        EntrepotEntity entrepot = EntrepotEntity.builder()
                .nom("Grenis")
                .lettre("G")
                .adresse("31 rue Pierre Mendes France")
                .codePostal("38320")
                .ville("Eybens")
                .build();

        JourneeEntity journee = JourneeEntity.builder()
                .date(LocalDate.of(2024, 1, 1))
                .entrepot(entrepot)
                .build();

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .journee(journee)
                .build();

        LivraisonEntity entity = LivraisonEntity.builder()
                .numero(1)
                .etat(EtatsDeLivraison.PLANIFIEE)
                .tournee(tournee)
                .build();

        CommandeEntity commande = CommandeEntity.builder()
                .reference("c001")
                .dateCreation(LocalDateTime.now())
                .etat(EtatsDeCommande.PLANIFIEE)
                .client(client)
                .livraison(entity)
                .build();

        entity.setCommandes(Set.of(commande));
        client.setCommandes(Set.of(commande));

        ClientDTO clientDTO = ClientDTO.builder()
                .email(client.getEmail())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .etat(EtatsDeClient.A_LIVRER)
                .adresse(client.getAdresse())
                .codePostal(client.getCodePostal())
                .ville(client.getVille())
                .commandes(Set.of(commande.getReference()))
                .build();

        VisualiserLivraisonDTO dto = VisualiserLivraisonDTO.builder()
                .reference("l001G-A1")
                .numero(1)
                .client(clientDTO)
                .commandes(Set.of(commande.getReference()))
                .build();

        assertThat(mapper.toDTO(entity)).isEqualTo(dto);
    }
}
