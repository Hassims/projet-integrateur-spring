package fr.uga.l3miage.integrator.response;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Set;

public class TourneeDTO {
    @Schema(description = "Référence identifiant la tournée.", example = "t001G-A")
    private String reference;
    @Schema(description = "Etat de la tournée.", example = "PLANIFIEE")
    private EtatsDeTournee etat;
    @Schema(description = "Lettre de la tournée.", example = "A")
    private String lettre;
    @Schema(description = "Temps de montage renseigné par le livreur.", example = "60")
    private Integer tempsMontageEffectif;
    @Schema(description = "Montant cumulé des livraisons de la tournée.", example = "100.0")
    private Double montant;
    @Schema(description = "Temps de montage théorique cumulé des livraisons de la tournée.", example = "60")
    private Integer tempsMontageTheorique;
    @Schema(description = "Référence identifiant la journée pour laquelle est planifiée la tournée.", example = "j001G")
    private String journee;
    @Schema(description = "Ensemble des trigrammes des livreurs qui effectuent la tournée.", example = "[\"STR\", \"EBD\", \"SWL\"]")
    private Set<String> livreurs;
    @Schema(description = "Immatriculation du camion utilisé pour effectuer la tournée.", example = "AB-123-CD")
    private String camion;
    @Schema(description = "Liste ordonnée des références des livraisons à effectuer lors de la tournée.")
    private List<String> livraisons;
}
