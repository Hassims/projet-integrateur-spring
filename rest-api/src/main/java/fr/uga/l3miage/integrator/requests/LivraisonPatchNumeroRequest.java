package fr.uga.l3miage.integrator.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "Requête visant à mettre à jour le numéro et optionnellement la tournée d'une livraison.")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class LivraisonPatchNumeroRequest {
    @Schema(example = "1")
    private final int numero;
    @Schema(example = "t001G-A")
    private final String tournee;
}
