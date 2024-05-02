package fr.uga.l3miage.integrator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Organisation de livraisons pour une journée donnée.")
public class VisualiserUneTourneeDTO {
    @Schema(description = "Référence identifiant la tournée.", example = "t001G-A")
    private String reference;
    @Schema(description = "Date à laquelle s'effectuera la tournée.", example = "2024-01-01")
    private LocalDate date;
    @Schema(description = "Référence identifiant la journée pour laquelle est planifiée la tournée.", example = "j001G")
    private String journee;
    @Schema(description = "Nom de l'entrepôt pour lequel est planifiée la journée de la tournée.", example = "Grenis")
    private String entrepot;
    @Schema(description = "Ensemble des trigrammes des livreurs qui effectuent la tournée.", example = "[\"STR\", \"EBD\", \"SWL\"]")
    private Set<String> livreurs;
    @Schema(description = "Immatriculation du camion utilisé pour effectuer la tournée.", example = "AB-123-CD")
    private String camion;
    @Schema(description = "Liste ordonnée des livraisons à effectuer lors de la tournée.")
    private List<LivraisonDTO> livraisons;
}
