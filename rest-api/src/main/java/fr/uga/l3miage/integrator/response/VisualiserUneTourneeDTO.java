package fr.uga.l3miage.integrator.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisualiserUneTourneeDTO {
    private String journee;
    private String reference;
    private Date date;
    private String entrepot;
    private Set<String> livreurs;
    private String camion;
    private List<LivraisonDTO> livraisons;
}
