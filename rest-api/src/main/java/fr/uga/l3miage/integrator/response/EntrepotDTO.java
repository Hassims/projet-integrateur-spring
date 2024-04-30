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
public class EntrepotDTO {
    private String nom;
    private String lettre;
    private String adresse;
    private String codePostal;
    private String ville;
    private Set<Long> stocks;
    private Set<Long> journees;
    private Set<String> employes;
    private Set<String> camions;
}
