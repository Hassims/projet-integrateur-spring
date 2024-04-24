package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.components.TourneeComponent;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VisualiserTourneeService {

    private final TourneeRepository tourneeRepository;
    private final TourneeComponent tourneeComponent;

    /*
    public Set<TourneeEntity> allTournees(){
        try {
            return new HashSet(tourneeRepository.findAll());
        } catch (Exception e) {
            throw e;
        }
    }
    */

    public TourneeEntity findTournee(String reference) {
        return tourneeComponent.findByReference(reference);
    }
}
