package fr.uga.l3miage.integrator.entities;

import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientEntityTest {

    @Test
    void getEtatInscritCommandesNull() {
        ClientEntity client = ClientEntity.builder().build();
        assertThat(client.getEtat()).isEqualTo(EtatsDeClient.INSCRIT);
    }

    @Test
    void getEtatInscritCommandesEmpty() {
        ClientEntity client = ClientEntity.builder().commandes(Set.of()).build();
        assertThat(client.getEtat()).isEqualTo(EtatsDeClient.INSCRIT);
    }

    @Test
    void getEtatLivrable() {

        ClientEntity client = ClientEntity.builder().build();

        CommandeEntity commandeOuverte = CommandeEntity.builder()
                .etat(EtatsDeCommande.OUVERTE)
                .client(client)
                .build();

        CommandeEntity commandePlanifiee = CommandeEntity.builder()
                .etat(EtatsDeCommande.PLANIFIEE)
                .client(client)
                .build();

        CommandeEntity commandeEnLivraison = CommandeEntity.builder()
                .etat(EtatsDeCommande.EN_LIVRAISON)
                .client(client)
                .build();

        CommandeEntity commandeLivree = CommandeEntity.builder()
                .etat(EtatsDeCommande.LIVREE)
                .client(client)
                .build();

        CommandeEntity commandeNotee = CommandeEntity.builder()
                .etat(EtatsDeCommande.NOTEE)
                .client(client)
                .build();

        client.setCommandes(
                Set.of(commandeOuverte, commandePlanifiee, commandeEnLivraison, commandeLivree, commandeNotee)
        );

        assertThat(client.getEtat()).isEqualTo(EtatsDeClient.LIVRABLE);
    }

    @Test
    void getEtatAlivrer() {

        ClientEntity client = ClientEntity.builder().build();

        CommandeEntity commandePlanifiee = CommandeEntity.builder()
                .etat(EtatsDeCommande.PLANIFIEE)
                .client(client)
                .build();

        CommandeEntity commandeEnLivraison = CommandeEntity.builder()
                .etat(EtatsDeCommande.EN_LIVRAISON)
                .client(client)
                .build();

        CommandeEntity commandeLivree = CommandeEntity.builder()
                .etat(EtatsDeCommande.LIVREE)
                .client(client)
                .build();

        CommandeEntity commandeNotee = CommandeEntity.builder()
                .etat(EtatsDeCommande.NOTEE)
                .client(client)
                .build();

        client.setCommandes(Set.of(commandePlanifiee, commandeEnLivraison, commandeLivree, commandeNotee));

        assertThat(client.getEtat()).isEqualTo(EtatsDeClient.A_LIVRER);
    }

    @Test
    void getEtatLivre() {

        ClientEntity client = ClientEntity.builder().build();

        CommandeEntity commandeLivree = CommandeEntity.builder()
                .etat(EtatsDeCommande.LIVREE)
                .client(client)
                .build();

        CommandeEntity commandeNotee = CommandeEntity.builder()
                .etat(EtatsDeCommande.NOTEE)
                .client(client)
                .build();

        client.setCommandes(Set.of(commandeLivree, commandeNotee));

        assertThat(client.getEtat()).isEqualTo(EtatsDeClient.LIVRE);
    }
}
