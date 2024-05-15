package fr.uga.l3miage.integrator.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreerTourneeDTO {
    @Schema(description = "Lettre de la tournée.", example = "A")
    private String lettre;
    @Schema(example = "[]")
    private List<CreerLivraisonDTO> livraisons;
}
