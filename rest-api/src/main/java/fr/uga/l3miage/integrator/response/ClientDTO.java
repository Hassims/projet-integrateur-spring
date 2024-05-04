package fr.uga.l3miage.integrator.response;

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
@Schema(description = "")
public class ClientDTO {
    @Schema(description = "", example = "")
    private String email;
    @Schema(description = "", example = "")
    private String prenom;
    @Schema(description = "", example = "")
    private String nom;
    @Schema(description = "", example = "")
    private String adresse;
    @Schema(description = "", example = "")
    private String codePostal;
    @Schema(description = "", example = "")
    private String ville;
    @Schema(description = "", example = "")
    private Set<String> commandes;
}
