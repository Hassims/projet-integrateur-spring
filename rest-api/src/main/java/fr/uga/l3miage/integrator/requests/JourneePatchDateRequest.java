package fr.uga.l3miage.integrator.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Requête visant à mettre à jour la date d'une journée.")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class JourneePatchDateRequest {
    @Schema(description = "Date de la journée.", example = "2024-01-01")
    private final LocalDate date;
}
