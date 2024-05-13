package fr.uga.l3miage.integrator.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@Schema(description = "Requête visant à créer une journée.")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ConstruireJourneePostRequest {
    @Schema(description = "Date de la journée.", example = "2024-01-01")
    private final LocalDate date;
    @Schema(description = "Nom de l'entrepôt associé à la journée.", example = "Grenis")
    private final String nomEntrepot;
    @Schema(example = "[{\"lettre\": \"A\",\"livraisons\":[{\"commandes\": [\"c001\"]}]}]")
    private final Set<CreerTourneeDTO> tournees;
}
