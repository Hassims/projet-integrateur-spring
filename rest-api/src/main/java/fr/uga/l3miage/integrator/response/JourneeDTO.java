package fr.uga.l3miage.integrator.response;

import fr.uga.l3miage.integrator.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Journée ouvrée pendant laquelle différentes tournées sont planifiées par un planificateur pour un entrepôt donné.")
public class JourneeDTO {
    @Schema(description = "Référence identifiant la journée.", example = "j001G")
    private String reference;
    @Schema(description = "Etat de la journée.", example = "PLANIFIEE")
    private EtatsDeJournee etat;
    @Schema(description = "Date de la journée.", example = "2024-01-01")
    private LocalDate date;
    @Schema(description = "Nom de l'entrepôt pour lequel la journée est planifiée.", example = "Grenis")
    private String entrepot;
    @Schema(description = "Ensemble des références des tournées planifiées pour la journée.", example = "[\"t001G-A\", \"t002G-B\", \"t003G-C\"]")
    private Set<String> tournees;
    @Schema(description = "Montant (en €) cumulé de l'ensemble des commandes des tournées de la journée.")
    private Double montant;
    @Schema(description = "Temps de montage théorique (en minutes) cumulé de l'ensemble des commandes des tournées de la journée.")
    private Integer tempsMontageTheorique;
}
