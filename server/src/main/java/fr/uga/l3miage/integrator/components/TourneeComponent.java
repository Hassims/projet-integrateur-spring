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

    public TourneeEntity findByReference(String reference) throws Exception {
        return tourneeRepository.findAll()
                .stream()
                .filter(t -> t.getReference().equals(reference))
                .findFirst()
                .orElseThrow();
    }
}
