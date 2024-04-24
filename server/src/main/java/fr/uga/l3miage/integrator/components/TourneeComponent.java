package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourneeComponent {

    private final TourneeRepository tourneeRepository;

    public TourneeEntity findByReference(String reference) {
        for (TourneeEntity tournee :  tourneeRepository.findAll()){
            if (tournee.getReference().equals(reference)){
                return tournee ;
            }
        }
        throw new NotFoundEntityRestException("Tournée non trouvée");
    }
}
