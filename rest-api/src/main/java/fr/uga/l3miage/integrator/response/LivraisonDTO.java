package fr.uga.l3miage.integrator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivraisonDTO {
    private String reference;
    private String client;
    private String adresse;
    private String codePostal;
    private String ville;
    private Set<String> commandes;
}
