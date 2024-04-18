package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.repositories.VisualiserTourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourneeComponent {
    private final VisualiserTourneeRepository repository;
}
