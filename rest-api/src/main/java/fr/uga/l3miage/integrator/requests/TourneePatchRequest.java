package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Requête visant à mettre à jour l'état ou la lettre d'une tournée.")
public class TourneePatchRequest {
    private final EtatsDeTournee etat;
    private final String lettre;
}
