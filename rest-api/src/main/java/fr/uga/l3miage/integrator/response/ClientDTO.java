package fr.uga.l3miage.integrator.response;

import fr.uga.l3miage.integrator.enums.EtatsDeClient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Individu qui se rend sur le site de Mobilis. Il peut passer une commande pour des produits qu’il consulte dans le catalogue. Il dispose d’une adresse à laquelle sont livrées les commandes par les livreurs. Il peut également suivre une commande et noter une commande.")
public class ClientDTO {
    @Schema(description = "Adresse électronique identifiant le client.", example = "aalves@brt.fr")
    private String email;
    @Schema(description = "Prénom civil.", example = "ALVES")
    private String prenom;
    @Schema(description = "Nom de famille civil.", example = "ravi")
    private String nom;
    @Schema(description = "Etat du client au regard de l'état de ses commandes.", example = "INSCRIT")
    private EtatsDeClient etat;
    @Schema(description = "Adresse postale à laquelle le client reçoit ses commandes.", example = "3 Chemin des Tilleuls")
    private String adresse;
    @Schema(description = "Code à 5 chiffres de la ville dans laquelle le client reçoit ses commandes.", example = "38100")
    private String codePostal;
    @Schema(description = "Nom de la ville dans laquelle le client reçoit ses commandes.", example = "Grenoble")
    private String ville;
    @Schema(description = "Ensemble des références identifiant les commandes du client.", example = "[\"c001\", \"c002\", \"c003\"")
    private Set<String> commandes;
}
