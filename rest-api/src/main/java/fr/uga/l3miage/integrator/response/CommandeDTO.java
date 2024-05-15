package fr.uga.l3miage.integrator.response;

import fr.uga.l3miage.integrator.enums.EtatsDeCommande;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Commande passée par un client puis planifiée par le planificateur  et finalement livrée par des livreurs.")
public class CommandeDTO {
    private String reference;
    private EtatsDeCommande etat;
    private LocalDateTime dateCreation;
    private Integer note;
    private String commentaire;
    private Integer tddEffectif;
    private Integer tdmEffectif;
    private String client;
    private String livraison;
    private Double montant;
    private Integer tempsMontageTheorique;
}
