package fr.uga.l3miage.integrator.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "Requête visant à mettre à jour la journée d'une tournée.")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TourneePatchJourneeRequest {
    @Schema(example = "j001G")
    private final String journee;
}
