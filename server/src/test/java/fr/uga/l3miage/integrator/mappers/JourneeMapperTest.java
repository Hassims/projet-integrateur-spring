package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;
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
                .entrepot(entrepot)
                .build();

        TourneeEntity tournee = TourneeEntity.builder()
                .lettre("A")
                .etat(EtatsDeTournee.PLANIFIEE)
                .journee(entity)
                .build();

        LivraisonEntity livraison = LivraisonEntity.builder()
                .tournee(tournee)
                .build();

        CommandeEntity commande = CommandeEntity.builder()
                .livraison(livraison)
                .build();

        ProduitEntity produit = ProduitEntity.builder()
                .prix(9.99)
                .tdmTheorique(5)
                .build();

        LigneEntity ligne = LigneEntity.builder()
                .quantite(3)
                .commande(commande)
                .produit(produit)
                .build();

        tournee.setLivraisons(List.of(livraison));
        livraison.setCommandes(Set.of(commande));
        commande.setLignes(Set.of(ligne));

        entity.setTournees(Set.of(tournee));

        JourneeDTO dto = JourneeDTO.builder()
                .reference(entity.getReference())
                .etat(etat)
                .date(date)
                .montant(entity.getMontant())
                .tempsMontageTheorique(entity.getTempsMontageTheorique())
                .entrepot(entrepot.getNom())
                .tournees(Set.of(tournee.getReference()))
                .build();

        assertThat(mapper.toDTO(entity)).isEqualTo(dto);
    }
}
