package fr.uga.l3miage.integrator.requests;

import fr.uga.l3miage.integrator.enums.EtatsDeTournee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourneePatchRequest {
    private final EtatsDeTournee etat;
    private final String lettre;
    private final Integer tdmEffectif;
}
