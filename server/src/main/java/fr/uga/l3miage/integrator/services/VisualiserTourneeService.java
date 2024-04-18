package fr.uga.l3miage.integrator.services;

import fr.uga.l3miage.integrator.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.repositories.VisualiserTourneeRepository;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VisualiserTourneeService {
    private final VisualiserTourneeRepository tourneeRepository;

    public Set<TourneeEntity> allTournees(){
        try {
            return new HashSet(tourneeRepository.findAll());
        } catch (Exception e) {
            throw e;
        }
    }

    public TourneeEntity findTournee(String reference) {
        Optional<TourneeEntity> tournee = tourneeRepository.findById(reference);

        if (tournee.isPresent())
            return tournee.get();
        else
            throw new NotFoundEntityRestException("Tournée non trouvée");
    }
}
