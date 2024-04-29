package fr.uga.l3miage.integrator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntrepotsDTO {
    private String nom;
    private String lettre;
    private String adresse;
    private String codePostal;
    private String ville;
}
