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
@Schema(description = "Endroit où résident des stocks de produits. Point de départ de livraisons.")
public class EntrepotDTO {
    @Schema(description = "Nom identifiant l'entrepôt.", example = "Grenis")
    private String nom;
    @Schema(description = "Lettre utilisée pour construire les références des livraisons de l'entrepôt.", example = "G")
    private String lettre;
    @Schema(description = "Adresse postale localisant l'entrepôt.", example = "31 rue Pierre Mendes France")
    private String adresse;
    @Schema(description = "Code postal de la commune où se situe l'entrepôt.", example = "38320")
    private String codePostal;
    @Schema(description = "Nom de la ville où se situe l'entrepôt", example = "Eybens")
    private String ville;
    @Schema(description = "Ensemble des références des produits en stock dans l'entrepôt.", example = "[\"p01\", \"p02\", \"p03\"]")
    private Set<String> stocks;
    @Schema(description = "Ensemble des références des journées planifiées pour l'entrepôt.", example = "[\"j001G\", \"j002G\", \"j003G\"]")
    private Set<String> journees;
    @Schema(description = "Ensemble des trigrammes des employés qui travaillent dans l'entrepôt.", example = "[\"STR\", \"EBD\", \"SWL\"]")
    private Set<String> employes;
    @Schema(description = "Ensemble des immatriculations des camions utilisés par l'entrepôt.", example = "[\"AB-123-CD\", \"XY-456-ZT\", \"FG-789-LM\"]")
    private Set<String> camions;
}
