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
    @Schema(description = "Ensemble des références des commandes composant la livraison.", example = "[\"c001\", \"c002\", \"c003\"]")
    private Set<String> commandes;
}
