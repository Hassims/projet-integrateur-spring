package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.models.LigneEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.LivraisonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivraisonComponent {
    private final LivraisonRepository livraisonRepository ;

    public LivraisonEntity findByReference(String reference) throws Exception {
        return livraisonRepository.findAll()
                .stream()
                .filter(t -> t.getReference().equals(reference))
                .findFirst()
                .orElseThrow();
    }
}
