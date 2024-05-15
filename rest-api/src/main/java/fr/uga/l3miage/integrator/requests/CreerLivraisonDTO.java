package fr.uga.l3miage.integrator.requests;

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
public class CreerLivraisonDTO {
    @Schema(example = "[\"c001\", \"c002\", \"c003\"]")
    Set<String> commandes;
}
