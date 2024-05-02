package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Requête visant à mettre à jour l'état d'une livraison.")
public class LivraisonPatchEtatRequest {
    private final EtatsDeLivraison etat;
}
