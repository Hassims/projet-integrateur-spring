package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TourneeComponent {

    private final TourneeRepository tourneeRepository;

    public Optional<TourneeEntity> findByReference(String reference) {
        for (TourneeEntity tournee :  tourneeRepository.findAll()){
            if (tournee.getReference().equals(reference)){
                return Optional.of(tournee) ;
            }
        }
        return Optional.empty();
    }
}
