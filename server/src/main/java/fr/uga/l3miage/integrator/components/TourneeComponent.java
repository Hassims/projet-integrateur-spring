package fr.uga.l3miage.integrator.components;

import fr.uga.l3miage.integrator.repositories.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourneeComponent {
    private final TourneeRepository repository;
}
