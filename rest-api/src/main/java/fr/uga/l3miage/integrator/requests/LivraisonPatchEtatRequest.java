package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "Requête visant à mettre à jour l'état d'une livraison.")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class LivraisonPatchEtatRequest {
    private final EtatsDeLivraison etat;
}
