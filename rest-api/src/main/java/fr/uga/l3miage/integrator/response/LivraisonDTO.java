package fr.uga.l3miage.integrator.response;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
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
@Schema(description = "Opération consistant, pour les livreurs à livrer à un client une ou plusieurs commandes planifiées. Une livraison se fait dans le cadre d’une tournée.")
public class LivraisonDTO {
    @Schema(description = "Référence identifiant la livraison.", example = "l001G-A1")
    private String reference;
    @Schema(description = "Ordre de la livraison dans la tournée.", example = "1")
    private int numero;
    @Schema(description = "Etat de la livraison.", example = "PLANIFIEE")
    private EtatsDeLivraison etat;
    @Schema(description = "Adresse e-mail du client auquel appartiennent les commandes de la livraison.", example = "aalves@brt.fr")
    private String client;
    @Schema(description = "Adresse postale localisant le lieu de livraison.", example = "31 rue Pierre Mendes France")
    private String adresse;
    @Schema(description = "Code postal de la commune où se situe le lieu de livraison.", example = "38320")
    private String codePostal;
    @Schema(description = "Nom de la ville où se situe le lieu de livraison", example = "Eybens")
    private String ville;
    @Schema(description = "Ensemble des références des commandes composant la livraison.", example = "[\"c001\", \"c002\", \"c003\"]")
    private Set<String> commandes;
}
